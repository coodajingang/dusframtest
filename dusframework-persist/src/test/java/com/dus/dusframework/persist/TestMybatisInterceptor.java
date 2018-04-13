package com.dus.dusframework.persist;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.dus.dusframework.context.RunContextUtil;
import com.dus.dusframework.context.page.Page;
import com.dus.dusframework.service.impl.AppLogFilterService;
import com.dus.simulate.domain.AppLogFilterInfoDo;

public class TestMybatisInterceptor {

	@Test
	public void test4Interceptor() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mybatis.xml","classpath:application.xml"});
		
		DataSourceTransactionManager man = (DataSourceTransactionManager) context.getBean("transactionManager");
		
		System.out.println(man.toString());
		
		AppLogFilterService service  = (AppLogFilterService) context.getBean("appLogFilterService");
		
		System.out.println("============start=============");
		service.query("800");
		System.out.println("end!");
	}
	
	@Test
	public void test4Interceptor2() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mybatis.xml","classpath:application.xml"});
		
		DataSourceTransactionManager man = (DataSourceTransactionManager) context.getBean("transactionManager");
		
		System.out.println(man.toString());
		
		AppLogFilterService service  = (AppLogFilterService) context.getBean("appLogFilterService");
		
		AppLogFilterInfoDo mmdo = service.query("800");
		System.out.println(mmdo.toString());
		
		System.out.println("============start=============");
		
		for (int i = 0 ; i < 33; ++i) {
			mmdo.setFilterCode(mmdo.getFilterCode() + ":" + i);
			service.insert(mmdo);
		}
		
		System.out.println("end!");
	}
	
	@Test
	public void test4InterceptorPage() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mybatis.xml","classpath:application.xml"});
		
		DataSourceTransactionManager man = (DataSourceTransactionManager) context.getBean("transactionManager");
		
		RunContextUtil.createRunContext();
		
		Page page = new Page(1,3);
		
		RunContextUtil.setNeedPage(true);
		RunContextUtil.setPageInfo(page);
		
		System.out.println(man.toString());
		
		AppLogFilterService service  = (AppLogFilterService) context.getBean("appLogFilterService");
		
		List<String> codlist = new ArrayList<String>();
		
		System.out.println("============start=============");
		for (int i = 1; i< 10; ++i) {
			System.out.println("开始查询：" +i);
			page.setStartPage(i);
			List<AppLogFilterInfoDo> mdolist = service.queryAll("800");
			for (AppLogFilterInfoDo mdo : mdolist) {
				System.out.println(mdo.toString());
				codlist.add(mdo.getFilterCode());
			}
		}
		
		
		
		System.out.println("end!");
		int j = 0;
		for (String code : codlist) {
			System.out.println(j++ + ":" + code);
		}
	}
}
