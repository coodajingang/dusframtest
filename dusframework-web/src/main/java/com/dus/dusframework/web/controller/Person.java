package com.dus.dusframework.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dus.dusframework.web.test.TestBean;

@Component("person")
public class Person {

	public Person() {
		System.out.println("Con.... Person........");
	}
	
	//@Autowired
	//private TestBean test;
}
