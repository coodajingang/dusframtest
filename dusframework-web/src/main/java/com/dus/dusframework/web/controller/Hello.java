package com.dus.dusframework.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String hello(@RequestParam(value="name", required=false) String name ,Model model) {
		System.out.println("==================");  
		
		//Object obj = SpringAppContextUtil.getBean("person");
		//System.out.println("==" + obj);
		System.out.println("===" + this.dao);
		System.out.println("===" + name);
		model.addAttribute("home", "adkkdkdkdkdkdkdkkddkk");
		return "hello"; 
	}
	
	@GetMapping("/world")
	@ResponseBody
	public String world(@RequestParam(value="name") String name) {
		System.out.println("==================" + name );  
		
		//Object obj = SpringAppContextUtil.getBean("person");
		//System.out.println("==" + obj);
		System.out.println("===" + this.dao);
		
		return "world中文收到！"; 
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
