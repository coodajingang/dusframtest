package com.dus.dusframework.web.test;

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
}
