package com.dus.dusframework.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dus.dusframework.web.dao.DusDictBaseFieldDao;

@RestController
public class Hello {

	@Autowired
	private DusDictBaseFieldDao dao;
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("==================");
		
		//Object obj = SpringAppContextUtil.getBean("person");
		//System.out.println("==" + obj);
		System.out.println("===" + this.dao);
		return "In hello controller!";
	}
}
