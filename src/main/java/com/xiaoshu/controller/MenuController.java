package com.xiaoshu.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.service.MenuService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RequestMapping("menu")
@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private OperationService operationService;
	
	static Logger logger = Logger.getLogger(MenuController.class);
	
	
	@RequestMapping("menuIndex")
	public String index(HttpServletRequest request,HttpServletResponse response,Long menuid){
		String currentOperationIds = (String) request.getSession().getAttribute("currentOperationIds");
		String[] OperationIdArr = currentOperationIds.split(",");
		List<Operation> operationList = operationService.findByMenuId(menuid);
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		for (Operation operation : operationList) {
			if(StringUtil.existStrArr(operation.getOperationId().toString(),OperationIdArr)){
				map.put(operation.getOperationCode(),true);
			}else{
				map.put(operation.getOperationCode(),false);
			}
		}
		request.setAttribute("operationInfo", map);
		return "menu";
	}
	
	@RequestMapping("treeGridMenu")
	public void treeGridMenu(HttpServletRequest request,HttpServletResponse response){
		try {
			String parentId = request.getParameter("parentId");
			JSONArray jsonArray = getListByParentId(parentId,0);
			WriterUtil.write(response, jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("菜单展示错误",e);
		}
	}
	
	public JSONArray getListByParentId(String parentId, int l)throws Exception{
		JSONArray jsonArray = this.getTreeGridMenuByParentId(parentId,l);
		JSONArray resultJsonArray = new JSONArray();
		
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			resultJsonArray.add(jsonObject);
			if(!"isParent".equals(jsonObject.getString("state"))){
				continue;
			}else{
				resultJsonArray.addAll(getListByParentId(jsonObject.getString("menuid"),++l));
			}
		}
		return resultJsonArray;
	}
	
	
	public JSONArray getTreeGridMenuByParentId(String parentId, int l)throws Exception{
		JSONArray jsonArray = new JSONArray();
		List<Menu> list = menuService.findByParentId(Long.parseLong(parentId));
		for(Menu m : list){
			JSONObject jsonObject = new JSONObject();
			Long menuId = m.getMenuId();
			jsonObject.put("menuid", menuId);
			jsonObject.put("menuname", m.getMenuName());
			jsonObject.put("parentid", m.getParentId());
			jsonObject.put("iconcls", m.getIconCls());
			jsonObject.put("state", m.getState());
			jsonObject.put("seq", m.getSeq());
			jsonObject.put("menuurl", m.getMenuUrl());
			jsonObject.put("menudescription", m.getMenuDescription());
			jsonObject.put("level", l);
			jsonObject.put("isLeaf", (StringUtil.isEmpty(m.getState())||"close".equals(m.getState()) ));
			jsonObject.put("parent", m.getParentId().compareTo(new Long(0))>0?m.getParentId():null);
			jsonObject.put("laoded", true);
			jsonObject.put("expanded", true);
			
			// 加上该页面菜单下面的按钮
			List<Operation> operaList = operationService.findByMenuId(menuId);
			if (operaList!=null && operaList.size()>0) {
				String string = "";
				for (Operation o : operaList) {
					string += o.getOperationName() + ",";
				}
				jsonObject.put("operationnames", string.substring(0,string.length()-1));
			} else {
				jsonObject.put("operationnames", "");
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	
	
	@RequestMapping({"reserveMenu"})
	public void reserveMenu(HttpServletRequest request,HttpServletResponse response,Menu menu){
		String menuId = menu.getMenuId()==null?"":menu.getMenuId().toString();
		JSONObject result = new JSONObject();
		try {
			if (StringUtil.isNotEmpty(menuId)) {  //更新操作
				menuService.updateMenu(menu);
			} else {
				String parentId = menu.getParentId()==null?"":menu.getParentId().toString();
				menu.setParentId(Long.parseLong(parentId));
				if (isLeaf(parentId)) {
					// 添加操作
					if("1".equals(parentId)){
						menu.setState("close");
					}
					menuService.addMenu(menu);  
					
					// 更新他上级状态。变成isParent
					menu = new Menu();
					menu.setMenuId(Long.parseLong(parentId));
					menu.setState("isParent");
					menuService.updateMenu(menu);
				} else {
					// 添加操作
					if("1".equals(parentId)){
						menu.setState("close");
					}
					menuService.addMenu(menu);
				}
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("菜单保存错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败！");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	
	// 判断是不是叶子节点
	public boolean isLeaf(String menuId){
		boolean flag = false;
		try {
			List<Menu> list = menuService.findByParentId(Long.parseLong(menuId));
			if (list==null || list.size()==0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("判断菜单是否叶子节点错误",e);
		}
		return flag;
	}

	@RequestMapping("deleteMenu")
	public void deleteMenu(HttpServletRequest request,HttpServletResponse response,Long id){
		JSONObject result = new JSONObject();
		try {
			Menu menu = menuService.findByMenuId(id);
			String parentId = menu.getParentId().toString();
			if (!isLeaf(id.toString())) {  //不是叶子节点，说明有子菜单，不能删除
				result.put("errorMsg", "该菜单下面有子菜单，不能直接删除");
			} else {
				long sonNum = menuService.countMenuByParentId(Long.parseLong(parentId));
				if (sonNum == 1) {  
					// 只有一个孩子，删除该孩子，且把父亲状态置为""或close
					Menu parentMenu = menuService.findByMenuId(Long.parseLong(parentId));
					if(parentMenu.getParentId().compareTo(1L) == 0){
						menu.setState("close");
					}else{
						menu.setState("");
					}
					menuService.updateMenu(menu);
					
					menuService.deleteMenu(id);
				} else {
					//不只一个孩子，直接删除
					menuService.deleteMenu(id);
				}
				result.put("success", true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("菜单删除错误",e);
			result.put("errorMsg", "对不起，删除失败！");
		}
		WriterUtil.write(response, result.toString());
	}
	
}
