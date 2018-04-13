package com.dus.dusframework.web.util;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.dus.dusframework.web.config.RootConfig;

public class SpringAppContextUtil {

	private SpringAppContextUtil() {
		
	}
	
	private static AnnotationConfigWebApplicationContext ctx;
	
    static {
        ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(RootConfig.class);
        ctx.refresh();
    }
    
    public static Object getBean(String name) {
        return ctx.getBean(name);
    }
}
