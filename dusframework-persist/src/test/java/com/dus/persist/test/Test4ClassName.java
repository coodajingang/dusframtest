package com.dus.persist.test;

import org.junit.Test;

public class Test4ClassName {

	@Test
	public void test4classname() {
		System.out.println(this.getClass().getName());
		System.out.println(this.getClass().getSimpleName());
		System.out.println(this.getClass().getPackage());
		
		System.out.println("================================");
		
		B b = new B();
		
		System.out.println(b.getClass().getName());
		System.out.println(b.getClass().getSimpleName());
		System.out.println(b.getClass().getPackage());
		System.out.println(b.getNamespace());
		System.out.println(b.getEntityClass().toString());
	}
}
