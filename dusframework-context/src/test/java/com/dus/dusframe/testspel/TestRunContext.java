package com.dus.dusframe.testspel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.dus.dusframework.context.RunContext;

public class TestRunContext {

	@Test
	public void test4context() {
		RunContext context = new RunContext();
		
		context.setValue("['www']", 1234);
		context.setValue("['http']", new AtomicInteger(30));
		
		System.out.println(context.getValue("['www']"));
		
		AtomicInteger inter = context.getValue("['http']", AtomicInteger.class);
		
		System.out.println(inter.get());
		
		for (Object key : context.keySet()) {
			System.out.println((String)key);
		}
		
		for (Object obj : context.values()) {
			System.out.println(obj.toString());
		}
	}
}
