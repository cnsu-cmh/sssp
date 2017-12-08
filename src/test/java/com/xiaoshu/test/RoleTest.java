package com.xiaoshu.test;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.service.MenuService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springDispatcherServlet-servlet.xml" })
public class RoleTest {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OperationService operationService;
	
	@Test
	public void addTest(){
		try {
			Set<Menu> menus = menuService.findAll();
			Set<Operation> operations = operationService.findAll();
			Role role = new Role();
			role.setRoleName("超级管理员");
			role.setRoleDescription("拥有全部权限的超级管理员角色");
			role.setMenuIds(menus);
			role.setOperationIds(operations);
			roleService.addRole(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
