package com.xiaoshu.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.service.MenuService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	static Logger logger = Logger.getLogger(RoleController.class);
	
	
	@RequestMapping("roleIndex")
	public String index(HttpServletRequest request,Long menuid){
		List<Operation> operationList = operationService.findByMenuId(menuid);
		request.setAttribute("operationList", operationList);
		return "role";
	}
	
	@RequestMapping("roleList")
	public void userList(HttpServletRequest request,HttpServletResponse response,String offset,String limit){
		try {
			Role role = new Role();
			String rolename = request.getParameter("rolename");
			String order = request.getParameter("order");
			String ordername = request.getParameter("ordername");
			if (StringUtil.isNotEmpty(rolename)) {
				role.setRoleName(rolename);
			}
			role.setRoleName(rolename);
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			Page<Role> pageInfo = roleService.PageRoleByRoleName(rolename,pageNum,pageSize,ordername,order);
			JSONObject jsonObj = new JSONObject();
			request.setAttribute("rolename",rolename);
			jsonObj.put("total",pageInfo.getTotalPages() );
			jsonObj.put("rows", pageInfo.getContent());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("角色展示错误",e);
		}
	}
	
	@RequestMapping("reserveRole")
	public void addUser(HttpServletRequest request,Role role,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			if (role.getRoleId() != null ) {
				roleService.updateRole(role);
				result.put("success", true);
			}else {
				if(roleService.existRoleWithRoleName(role.getRoleName())==null){  // 没有重复可以添加
					roleService.addRole(role);
					result.put("success", true);
				} else {
					result.put("success", true);
					result.put("errorMsg", "该角色名被使用");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("角色保存错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	
	@RequestMapping("deleteRole")
	public void delRole(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] roleIds = request.getParameter("ids").split(",");
			for (int i=0;i<roleIds.length;i++) {
				boolean b = userService.existUserWithRoleId(Long.parseLong(roleIds[i]))==null; //该角色下面没有用户
				if(!b){
					result.put("errorIndex", i);
					result.put("errorMsg", "有角色下面有用户，不能删除");
					WriterUtil.write(response, result.toString());
					return;
				}
			}
			if (roleIds.length==1) {
				roleService.deleteRole(Long.parseLong(roleIds[0]));
			} else {
				roleService.deleteRoleByRoleIds(roleIds);
			}
			result.put("success", true);
			result.put("delNums", roleIds.length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("角色删除错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	@RequestMapping("rightCtrl")
	public String chooseMenu(HttpServletRequest request,Integer roleid){
		request.setAttribute("roleid",roleid);
		return "rightCtrl";
	}
	
	@RequestMapping("chooseMenu")
	public void chooseMenu(HttpServletRequest request,HttpServletResponse response,String parentid,Long roleid){
		try {
			Role role = roleService.findOneRole(roleid);
			Set<Menu> menuIds = role.getMenuIds();
			Set<Operation> operationIds = role.getOperationIds();
			JSONArray jsonArray = getCheckedMenusByParentId(parentid, menuIds,operationIds,0);
			WriterUtil.write(response, jsonArray.toString());
			System.out.println(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加载权限菜单树错误",e);
		}
	}
	
	// 选中已有的角色
	public JSONArray getCheckedMenusByParentId(String parentId,Set<Menu> menuIds,Set<Operation> operationIds,int l)throws Exception{
		JSONArray resultJsonArray = new JSONArray();
		JSONArray jsonArray = this.getCheckedMenuByParentId(parentId,menuIds,operationIds,l);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			resultJsonArray.add(jsonObject);
			if(!"isParent".equals(jsonObject.getString("state"))){
				continue;
			}else{
				resultJsonArray.addAll(getCheckedMenusByParentId(jsonObject.getString("menuid"),menuIds,operationIds,++l));
			}
		}
		return resultJsonArray;
	}
	
	public JSONArray getCheckedMenuByParentId(String parentId,Set<Menu> menuIds,Set<Operation> operationIds,int l)throws Exception{
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
			jsonObject.put("parent", m.getParentId().compareTo(0L)>0?m.getParentId():null);
			jsonObject.put("laoded", true);
			jsonObject.put("expanded", true);
			if (menuIds.size() > 0) {
				if (menuIds.contains(m)) {
					jsonObject.put("checked", true);
				} 	
			}
			jsonObject.put("operations", getOperationJsonArray(menuId,operationIds));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	
	public JSONArray getOperationJsonArray(Long menuId,Set<Operation> operationIds){
		JSONArray jsonArray = new JSONArray();
		try {
			List<Operation> list = operationService.findByMenuId(menuId);
			for(Operation o : list){
				JSONObject jsonObject = new JSONObject();
				Long operationId = o.getOperationId();
				jsonObject.put("operationid", operationId);
				jsonObject.put("operationname", o.getOperationName());
				jsonObject.put("iconcls", o.getIconCls());
				if (operationIds.size() > 0) {
					if (operationIds.contains(operationId)) {
						jsonObject.put("checked", true);
					} 	
				}
				jsonArray.add(jsonObject);
			}
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("updateRoleMenu")
	public void updateRoleMenu(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		try {
			String roleid = request.getParameter("roleid");
			String menuids = request.getParameter("menuids");
			String operationids = request.getParameter("operationids");
			Role role = new Role();
			role.setRoleId(Long.parseLong(roleid));
			if (StringUtil.isNotEmpty(menuids)) {
				String[] menuArrIds = menuids.split(",");
				Set<Menu> menuidsStr = getMenuIdAndParentMenuId(menuArrIds);
				role.setMenuIds(menuidsStr);
			}
			
			if (StringUtil.isNotEmpty(operationids)) {
				Set<Operation> operationidSet = new HashSet<Operation>();
				for (String operationid : operationids.split(",")) {
					operationidSet.add(operationService.findByOperationId(Long.parseLong(operationid)));
				}
				role.setOperationIds(operationidSet);
			}
			
			roleService.updateRole(role);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("授权失败",e);
			result.put("errorMsg", "对不起，授权失败");
		}
		WriterUtil.write(response, result.toString());
	}

	private Set<Menu> getMenuIdAndParentMenuId(String[] menuArrIds) {
		Set<Menu> menuSetIds = new HashSet<Menu>();
		for (String menuid : menuArrIds) {
			menuSetIds.add(menuService.findByMenuId(Long.parseLong(menuid)));
		}
		Iterator<Menu> it = menuSetIds.iterator();
		Set<Menu> menuIds = new HashSet<Menu>();
		while (it.hasNext()) {
			Menu menu= it.next();
			addParentMenuid(menuSetIds,menuIds,menu);
		}
		menuSetIds.addAll(menuIds);
		return menuSetIds;
	}
	
	private void addParentMenuid(Set<Menu> menuSetIds,Set<Menu> menuIds,Menu menu){
		Long parentId = menu.getParentId();
		if(parentId.compareTo(0L)>0){
			Menu parentMenu = menuService.findByMenuId(parentId);
			if(!menuSetIds.contains(parentMenu)){
				menuIds.add(parentMenu);
				addParentMenuid(menuSetIds,menuIds,parentMenu);
			}
		}
	}
}
