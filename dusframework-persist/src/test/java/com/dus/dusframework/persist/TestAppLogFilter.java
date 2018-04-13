package com.dus.dusframework.persist;



import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.dus.dusframework.service.IAppLogFilterService;
import com.dus.dusframework.service.impl.AppLogFilterService;
import com.dus.simulate.domain.AppLogFilterInfoDo;

public class TestAppLogFilter {

	@Test
	public void test4persist() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mybatis.xml","classpath:application.xml"});
		
		IAppLogFilterService service  = (IAppLogFilterService) context.getBean("appLogFilterService");
		
		System.out.println("=========================");
		AppLogFilterInfoDo mmdo = service.query("800");
		
		System.out.println(mmdo.toString());
		
		// 
		System.out.println("insert......");
		mmdo.setProjectId("9001");
		service.insert(mmdo);
	
		System.out.println("====query all=====================");
		List<AppLogFilterInfoDo> mmdolist = service.queryAll();
		
		System.out.println(mmdolist.size());
		
		for (AppLogFilterInfoDo mdo : mmdolist) {
			System.out.println(mdo.toString());
		}
		
	}
	
	@Test
	public void test4quierynull() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:application.xml","classpath:spring-mybatis.xml"});
		
		IAppLogFilterService service  = (IAppLogFilterService) context.getBean("appLogFilterService");
		
		System.out.println("=========================");
		AppLogFilterInfoDo mmdo = service.query("8010");
		
		if (mmdo != null) {
			System.out.println(mmdo.toString());
		}
		
	}
	
	@Test
	public void test4Transaction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mybatis.xml","classpath:application.xml"});
		
		DataSourceTransactionManager man = (DataSourceTransactionManager) context.getBean("transactionManager");
		
		System.out.println(man.toString());
		
		AppLogFilterService service  = (AppLogFilterService) context.getBean("appLogFilterService");
		
		System.out.println("=========================");
		service.addRecord();
		System.out.println("end!");
	}
}
