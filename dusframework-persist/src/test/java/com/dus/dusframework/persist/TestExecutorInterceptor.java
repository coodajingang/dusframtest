package com.dus.dusframework.persist;

import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts(value = { 
        @Signature (type=Executor.class,
                method="update",
                args={MappedStatement.class,Object.class}),
        @Signature(type=Executor.class,
        method="query",
        args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class,
                CacheKey.class,BoundSql.class}),
        @Signature(type=Executor.class,
        method="query",
        args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})})
public class TestExecutorInterceptor implements Interceptor{


	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("=====In intercept :" + invocation.toString());
		
		long begin = System.currentTimeMillis();
		
		Object res = null;
		
		res = invocation.proceed();
		
		System.out.println("====Method:" +invocation.getMethod());
		Object obj = invocation.getTarget();
		
//		Object[] args = invocation.getArgs();
//		
//		int i = 0;
//		for (Object oo : args) {
//			i++;
//			System.out.println(i + "======" + oo.getClass().getName());
//			if (oo instanceof MappedStatement) {
//				MappedStatement ms = (MappedStatement) oo;
//				System.out.println("======" + ms.getId());
//			}
//		}
		
		MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
		
		System.out.println("=====" + ms.getId());
		System.out.println("=====结束：耗时：" + (System.currentTimeMillis() - begin));
		
		return res;
	}


	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		System.out.println("==In plugin:" + target.getClass().getName());
		return Plugin.wrap(target, this);
	}


	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
