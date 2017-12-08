package com.xiaoshu.controller;


import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.service.MenuService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("operation")
public class OperationController {
	static Logger logger = Logger.getLogger(OperationController.class);
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private MenuService menuService;

	@RequestMapping("operationIndex")
	public String index(HttpServletRequest request,HttpServletResponse response,@RequestParam("menuId") String menuId){
		
		if(StringUtil.isNotEmpty(menuId)){
			Menu menu = menuService.findByMenuId(Long.parseLong(menuId));
			request.setAttribute("menuId",menuId);
			request.setAttribute("menuName",menu.getMenuName());
		}
		
		return "operation";
	}
	
	@RequestMapping("operationList")
	public void list(HttpServletRequest request,HttpServletResponse response,Long menuId,Integer page,Integer rows){
		try {
			Page<Operation> pageinfo = operationService.pageByMenuId(menuId,page,rows);
			JSONObject jsonObj = new JSONObject();//new一个JSON
			jsonObj.put("total",pageinfo.getTotalPages());
			jsonObj.put("records", pageinfo.getTotalElements());
			Object object = JSONArray.toJSON(pageinfo.getContent());
			/*Map<Long, Operation> collect = pageinfo.getContent().stream().collect(Collectors.toMap(Operation::getOperationId, operation -> operation));
			Collection<Operation> values = collect.values();*/
			jsonObj.put("rows", object);
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("按钮展示错误",e);
		}
		
	}
	
	@RequestMapping("reserveOperation")
	public void reserveMenu(HttpServletRequest request,HttpServletResponse response,Operation operation,Long menuid){
		Long operationId = operation.getOperationId();
		Menu menu = menuService.findByMenuId(menuid);
		operation.setMenuId(menu);
		JSONObject result=new JSONObject();
		try {
			if (operationId != null) {  //更新操作
				operationService.updateOperation(operation);
			} else {
				operationService.addOperation(operation);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("按钮保存错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败！");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	

	@RequestMapping("deleteOperation")
	public void delUser(HttpServletRequest request,HttpServletResponse response,Long id){
		JSONObject result=new JSONObject();
		try {
			operationService.deleteOperation(id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除按钮错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
}
