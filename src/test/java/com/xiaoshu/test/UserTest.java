package com.xiaoshu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springDispatcherServlet-servlet.xml" })
public class UserTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void addTest(){
		try {
			Role role = roleService.findOneRole(1L);
			User user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			user.setUserDescription("超级管理员，供开发方使用");
			user.setRoleId(role);
			userService.addUser(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
