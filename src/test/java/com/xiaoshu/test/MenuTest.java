package com.xiaoshu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xiaoshu.entity.Menu;
import com.xiaoshu.service.MenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springDispatcherServlet-servlet.xml" })
public class MenuTest {

	@Autowired
	private MenuService menuService;
	
	@Test
	public void addTest(){
		try {
			Menu menu1 = new Menu();
			menu1.setMenuName("SSSP系统");
			menu1.setMenuDescription("主菜单");
			menu1.setParentId(-1L);
			menu1.setState("isParent");
			menu1.setIconCls("fa fa-university");
			menu1.setSeq(1);
			menuService.addMenu(menu1);
			
			Menu menu2 = new Menu();
			menu2.setMenuName("系统管理");
			menu2.setParentId(1L);
			menu2.setState("isParent");
			menu2.setIconCls("fa fa-desktop");
			menu2.setSeq(1);
			menuService.addMenu(menu2);
			
			Menu menu3 = new Menu();
			menu3.setMenuName("修改密码");
			menu3.setMenuUrl("javascript:editPassword();//");
			menu3.setParentId(1L);
			menu3.setState("close");
			menu3.setIconCls("fa fa-key");
			menu3.setSeq(2);
			menuService.addMenu(menu3);
			
			Menu menu4 = new Menu();
			menu4.setMenuName("安全退出");
			menu4.setMenuUrl("logout.htm");
			menu4.setParentId(1L);
			menu4.setState("close");
			menu4.setIconCls("fa fa-user-times");
			menu4.setSeq(3);
			menuService.addMenu(menu4);
			
			Menu menu5 = new Menu();
			menu5.setMenuName("菜单管理");
			menu5.setMenuUrl("menu/menuIndex.htm");
			menu5.setParentId(2L);
			menu5.setIconCls("fa fa-sliders");
			menu5.setSeq(3);
			menuService.addMenu(menu5);
			
			Menu menu6 = new Menu();
			menu6.setMenuName("角色管理");
			menu6.setMenuUrl("role/roleIndex.htm");
			menu6.setParentId(2L);
			menu6.setIconCls("fa fa-users");
			menu6.setSeq(2);
			menuService.addMenu(menu6);
			
			Menu menu7 = new Menu();
			menu7.setMenuName("用户管理");
			menu7.setMenuUrl("user/userIndex.htm");
			menu7.setParentId(2L);
			menu7.setIconCls("fa fa-user");
			menu7.setSeq(1);
			menuService.addMenu(menu7);
			
			Menu menu8 = new Menu();
			menu8.setMenuName("日志管理");
			menu8.setMenuUrl("log/logIndex.htm");
			menu8.setParentId(2L);
			menu8.setIconCls("fa fa-tags");
			menu8.setSeq(4);
			menuService.addMenu(menu8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void updateTest(){
		try {
			Menu menu = new Menu();
			menu.setMenuId(1L);
			menu.setMenuName("SSSP系统");
			menu.setMenuDescription("主菜单");
			menu.setParentId(-1L);
			menu.setState("isParent");
			menu.setIconCls("a-university");
			menu.setSeq(1);
			menuService.addMenu(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
