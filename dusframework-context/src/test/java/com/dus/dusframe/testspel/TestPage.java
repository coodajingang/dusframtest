package com.dus.dusframe.testspel;

import org.junit.Test;

import com.dus.dusframework.context.IContextConstants;
import com.dus.dusframework.context.RunContext;
import com.dus.dusframework.context.RunContextUtil;
import com.dus.dusframework.context.page.Page;

public class TestPage {

	@Test
	public void test4page() {
		RunContext context = RunContextUtil.createRunContext();
		
		Page page = new Page(1, 30);
		
		System.out.println(page.toString());
		
		RunContextUtil.setPageInfo(page);
		
		
		Page tmp = RunContextUtil.getPageInfo();
		
		System.out.println(tmp.toString());
		
		tmp.setTotalRecords(1000);
		
		System.out.println(tmp.toString());
		
		System.out.println(page.toString());
		
	}
	
	@Test
	public void test4page2() {
		RunContext context = RunContextUtil.createRunContext();

		
		Page tmp = RunContextUtil.getPageInfo();
		
		System.out.println(RunContextUtil.getValue(IContextConstants.CONTEXT_NEEDPAGE));
		

		boolean need = RunContextUtil.getNeedPage();
		
		System.out.println(need);
		
		//System.out.println(tmp.toString());
		
	}
}
