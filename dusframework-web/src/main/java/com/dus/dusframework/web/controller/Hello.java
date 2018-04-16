package com.dus.dusframework.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dus.dusframework.web.dao.DusDictBaseFieldDao;

@Controller
public class Hello {

	public Hello() { 
		System.out.println("===s");
	}
	@Autowired
	private DusDictBaseFieldDao dao;
	
	@GetMapping("/hello")
	public String hello(Model model) {
		System.out.println("==================");  
		
		//Object obj = SpringAppContextUtil.getBean("person");
		//System.out.println("==" + obj);
		System.out.println("===" + this.dao);
		
		model.addAttribute("home", "adkkdkdkdkdkdkdkkddkk");
		return "hello"; 
	}
	
	@GetMapping("/world")
	public String world() {
		System.out.println("==================");  
		
		//Object obj = SpringAppContextUtil.getBean("person");
		//System.out.println("==" + obj);
		System.out.println("===" + this.dao);
		
		return "world"; 
	}
	@GetMapping("/index2")
	public String getindex() {
		System.out.println("==================");  
		
		//Object obj = SpringAppContextUtil.getBean("person");
		//System.out.println("==" + obj);
		System.out.println("===" + this.dao);
		
		return "index"; 
	}
}
