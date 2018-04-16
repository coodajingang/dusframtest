package com.dus.dusframework.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DictIndexController {
	@GetMapping("/index")
	public String getindex() {
		System.out.println("==================");  
		
		//Object obj = SpringAppContextUtil.getBean("person");
		//System.out.println("==" + obj);
		//System.out.println("===" + this.dao);
		
		return "index"; 
	}
}
