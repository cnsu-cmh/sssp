package com.xiaoshu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.service.MenuService;
import com.xiaoshu.service.OperationService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springDispatcherServlet-servlet.xml" })
public class OperationTest {
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private MenuService menuService;
	
	@Test
	public void addTest(){
		try {
			Menu menu1 = menuService.findByMenuId(5L);
			Operation operation1 = new Operation();
			operation1.setOperationCode("add");
			operation1.setOperationName("添加");
			operation1.setMenuId(menu1);
			operationService.addOperation(operation1);
			
			Operation operation2 = new Operation();
			operation2.setOperationCode("edit");
			operation2.setOperationName("修改");
			operation2.setMenuId(menu1);
			operationService.addOperation(operation2);
			
			Operation operation3 = new Operation();
			operation3.setOperationCode("del");
			operation3.setOperationName("删除");
			operation3.setMenuId(menu1);
			operationService.addOperation(operation3);
			
			Operation operation4 = new Operation();
			operation4.setOperationCode("btnCtrl");
			operation4.setOperationName("按钮管理");
			operation4.setMenuId(menu1);
			operationService.addOperation(operation4);
			
			
			Menu menu2 = menuService.findByMenuId(6L);
			Operation operation5 = new Operation();
			operation5.setOperationCode("btn_add");
			operation5.setOperationName("添加");
			operation5.setIconCls("glyphicon glyphicon-plus");
			operation5.setMenuId(menu2);
			operationService.addOperation(operation5);
			
			Operation operation6 = new Operation();
			operation6.setOperationCode("btn_edit");
			operation6.setOperationName("修改");
			operation6.setIconCls("glyphicon glyphicon-pencil");
			operation6.setMenuId(menu2);
			operationService.addOperation(operation6);
			
			Operation operation7 = new Operation();
			operation7.setOperationCode("btn_delete");
			operation7.setOperationName("删除");
			operation7.setIconCls("glyphicon glyphicon-remove");
			operation7.setMenuId(menu2);
			operationService.addOperation(operation7);
			
			Operation operation8 = new Operation();
			operation8.setOperationCode("btn_rightCtrl");
			operation8.setOperationName("授权");
			operation8.setIconCls("glyphicon glyphicon-eye-open");
			operation8.setMenuId(menu2);
			operationService.addOperation(operation8);
			
			Menu menu3 = menuService.findByMenuId(7L);
			Operation operation9 = new Operation();
			operation9.setOperationCode("btn_add");
			operation9.setOperationName("添加");
			operation9.setIconCls("glyphicon glyphicon-plus");
			operation9.setMenuId(menu3);
			operationService.addOperation(operation9);
			
			Operation operation10 = new Operation();
			operation10.setOperationCode("btn_edit");
			operation10.setOperationName("修改");
			operation10.setIconCls("glyphicon glyphicon-pencil");
			operation10.setMenuId(menu3);
			operationService.addOperation(operation10);
			
			Operation operation11 = new Operation();
			operation11.setOperationCode("btn_delete");
			operation11.setOperationName("删除");
			operation11.setIconCls("glyphicon glyphicon-remove");
			operation11.setMenuId(menu3);
			operationService.addOperation(operation11);
			
			Menu menu4 = menuService.findByMenuId(8L);
			Operation operation12 = new Operation();
			operation12.setOperationCode("btn_delete");
			operation12.setOperationName("删除");
			operation12.setIconCls("glyphicon glyphicon-remove");
			operation12.setMenuId(menu4);
			operationService.addOperation(operation12);
			
			Operation operation13 = new Operation();
			operation13.setOperationCode("btn_downloadLog4j");
			operation13.setOperationName("下载后台日志（log4j）");
			operation13.setIconCls("glyphicon glyphicon-download-alt");
			operation13.setMenuId(menu4);
			operationService.addOperation(operation13);
			
			Operation operation14 = new Operation();
			operation14.setOperationCode("btn_manualBackup");
			operation14.setOperationName("手动备份（业务操作）");
			operation14.setIconCls("glyphicon glyphicon-inbox");
			operation14.setMenuId(menu4);
			operationService.addOperation(operation14);
			
			Operation operation15 = new Operation();
			operation15.setOperationCode("btn_downloadLogBus");
			operation15.setOperationName("备份日志记录");
			operation15.setIconCls("glyphicon glyphicon-download");
			operation15.setMenuId(menu4);
			operationService.addOperation(operation15);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
