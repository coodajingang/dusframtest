package com.dus.simulate.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dus.dusframework.common.util.DateUtils;
import com.dus.dusframework.context.RunContextUtil;
import com.dus.dusframework.context.page.Page;
import com.dus.dusframework.service.IAppLogFilterService;
import com.dus.simulate.domain.AppLogFilterInfoDo;

public class AppLogFilterInfoDaoTest {

	@Test
	public void test4Select() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:application.xml","classpath:spring-mybatis.xml"});
		
		AppLogFilterInfoDao dao  = (AppLogFilterInfoDao) context.getBean("appLogFilterInfoDao");
		
		System.out.println("=========================");
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectId", "proid");
		dao.selectAll(map);
	}
	
	@Test
	public void test4Insert() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:application.xml","classpath:spring-mybatis.xml"});
		
		AppLogFilterInfoDao dao  = (AppLogFilterInfoDao) context.getBean("appLogFilterInfoDao");
		
		System.out.println("=========================");
		
		AppLogFilterInfoDo t = new AppLogFilterInfoDo();
		
		t.setBusiDate(DateUtils.getDate8Str());
		t.setChannelCgy("99");
		t.setChannelCode("001");
		t.setDacVerf("AAAAAAAA");
		t.setFilterCode("123456");
		t.setFilterType("type");
		t.setMultiEntity("hm");
		t.setProjectId("proid");
		t.setProjectName("proname");
		t.setRemark1("remark1");
		t.setRemark2("remark2");
		t.setSystemId("systemid");
		t.setSystemName("systemname");
		t.setTms(new Timestamp(System.currentTimeMillis()));
		t.setVno(1);
		int res = dao.insert(t);
		System.out.println("=====" + res);
	}
	
	@Test
	public void test4Selectbypage() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:application.xml","classpath:spring-mybatis.xml"});
		
		AppLogFilterInfoDao dao  = (AppLogFilterInfoDao) context.getBean("appLogFilterInfoDao");
		
		System.out.println("=========================");
		
		AppLogFilterInfoDo t = new AppLogFilterInfoDo();
		
		RunContextUtil.createRunContext();
		RunContextUtil.setNeedPage(true);
		Page page = new Page(3, 2);
		RunContextUtil.setPageInfo(page);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectId", "proid");
		
		List<AppLogFilterInfoDo> rlist = dao.selectByPage(map);
		
		System.out.println(rlist.size());
		for (AppLogFilterInfoDo app : rlist) {
			System.out.println(app.getFilterCode());
		}
		System.out.println(RunContextUtil.getPageInfo().toString());
	}
	
	@Test
	public void test4transaction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:application.xml","classpath:spring-mybatis.xml"});
		
		IAppLogFilterService service  = (IAppLogFilterService) context.getBean("appLogFilterService");
		
		System.out.println("=========================");
		
		AppLogFilterInfoDo t = new AppLogFilterInfoDo();
		
		t.setBusiDate(DateUtils.getDate8Str());
		t.setChannelCgy("99");
		t.setChannelCode("001");
		t.setDacVerf("AAAAAAAA");
		t.setFilterCode("123456");
		t.setFilterType("type");
		t.setMultiEntity("hm");
		t.setProjectId("proid123");
		t.setProjectName("proname");
		t.setRemark1("remark1");
		t.setRemark2("remark2");
		t.setSystemId("systemid123");
		t.setSystemName("systemname");
		t.setTms(new Timestamp(System.currentTimeMillis()));
		t.setVno(1);
		service.doNewAddRecords(t);
		System.out.println("=====");
	}
	
	@Test
	public void test4gensql() {
		String sql = new SQL()
				.INSERT_INTO("app_log_filter_info")
				.VALUES("BUSI_DATE, CHANNEL_CGY, CHANNEL_CODE,DAC_VERF, FILTER_CODE, FILTER_TYPE", 
						"#{busiDate}#,#{channelCgy}#,#{channelCode}#,#{dacVerf}#,#{filterCode}#,#{filterType}#").toString();
		System.out.println(sql);
	}
}
