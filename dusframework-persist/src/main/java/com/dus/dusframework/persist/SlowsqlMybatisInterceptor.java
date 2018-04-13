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
import org.slf4j.Logger;

/**
 * Mybatis的拦截器， Executor.class ， 统计耗时和 slowsql 
 * @author lenovo
 *
 */
@Intercepts(value = {
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
public class SlowsqlMybatisInterceptor implements Interceptor{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(SlowsqlMybatisInterceptor.class);
	
	private long slowSqlThreshold;
	

	public Object intercept(Invocation invocation) throws Throwable {
		//System.out.println("=====In intercept :" + invocation.toString());
		log.debug("开始slowsql拦截器处理");
		
		long begin = System.currentTimeMillis();
		
		Object res = null;
		String methodid = null;
		String mapid = null;
		
		try {
			methodid = invocation.getMethod().getName();
			MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
			mapid = ms.getId();
			return invocation.proceed();
		} finally {
			long cost = System.currentTimeMillis() - begin;
			log.info("Mybatis执行结束，method=[" + methodid + "] ID=[" + mapid + "] 耗时：[" + cost + "ms]");
			if (cost > this.slowSqlThreshold) {
				log.debug("发现slowSql ，耗时为" + cost);
				// TODO 写文件 
			}
		}
		
		/**
		System.out.println("====Method:" +invocation.getMethod());
		Object obj = invocation.getTarget();
		

		MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
		
		System.out.println("=====" + ms.getId());
		System.out.println("=====结束：耗时：" + (System.currentTimeMillis() - begin));
	**/
	}

	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		//System.out.println("==In plugin:" + target.getClass().getName());
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		if (this.slowSqlThreshold == 0) {
			this.slowSqlThreshold = IPersistConstants.SLOW_SQL_DEFAULT_THRESHOLD;
		}
		
	}

	public long getSlowSqlThreshold() {
		return slowSqlThreshold;
	}

	public void setSlowSqlThreshold(long slowSqlThreshold) {
		this.slowSqlThreshold = slowSqlThreshold;
	}
	
	
}
