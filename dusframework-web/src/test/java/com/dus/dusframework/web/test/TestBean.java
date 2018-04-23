package com.dus.dusframework.web.test;

import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * 测试该bean 是否可以通过@ComponentScan(basePackages = {"com.dus.dusframework"}) 扫描到  
 * 
 * @author ThinkPad
 *
 */
@Component
public class TestBean {
	public TestBean() {
		System.out.println("Construct TestBean!");
	}
	
	@Test
	public void test4Integer() {
		Integer aa = null;
		
		System.out.println(aa <= 0);
		System.out.println(aa > 0);
	}
}
