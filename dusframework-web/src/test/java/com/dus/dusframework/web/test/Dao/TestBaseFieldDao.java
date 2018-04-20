package com.dus.dusframework.web.test.Dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dus.dusframework.web.dao.DusDictBaseFieldDao;
import com.dus.dusframework.web.domain.DusDictBaseFieldDo;

public class TestBaseFieldDao {

	@Test
	public void test4SelectLikeBypage() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:dusframework.xml","classpath:spring-mybatis.xml"});
		
		DusDictBaseFieldDao dao  = (DusDictBaseFieldDao) context.getBean("DusDictBaseFieldDao");
		
		System.out.println("=========================");
		
		List<DusDictBaseFieldDo> res = dao.searchLikeByPage("");
		
		System.out.println("==" + res);
	}
}
