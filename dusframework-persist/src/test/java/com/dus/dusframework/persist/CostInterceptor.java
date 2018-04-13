package com.dus.dusframework.persist;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import com.dus.dusframework.common.util.ComUtils;

@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
	@Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
	@Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })})
public class CostInterceptor implements Interceptor{

	private long slowSqlThreshold;
	
	private static Pattern smartPattern;
	
	static {
		smartPattern = Pattern.compile("[\\s\n ]+");
	}
	
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		System.out.println("============In CostSqlInterceptor");
		long startTime = System.currentTimeMillis();
		
		StatementHandler statementHandler = (StatementHandler)target;
		
		try {
			return invocation.proceed();
		} finally {
			long endTime = System.currentTimeMillis();
			long sqlCost = endTime - startTime;
			
			BoundSql boundSql = statementHandler.getBoundSql();
			
			String sql = boundSql.getSql();
			Object parameterObject = boundSql.getParameterObject();
			List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
			
			sql = formatSql(sql, parameterObject, parameterMappingList);
			
			System.out.println("SQL：[" + sql + "]执行耗时[" + sqlCost + "ms]");
			
			if (sqlCost > this.slowSqlThreshold) {
				System.out.println("发现慢sql  ， 记录之 ！");
				System.out.println("SlowSql:" + sqlCost + "ms:" + sql );
				MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
				// BaseStatementHandler-->mappedStatement(delegate为mappedStatement属性名
				MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

				System.out.println("SlowSql id=" + mappedStatement.getId());
			}
		}

	}

	
	
	private String formatSql(String sql, Object paraObj, List<ParameterMapping> paraMapList) {
		if (ComUtils.isEmpty(sql)) {
			return "";
		}
		
		Matcher matcher = smartPattern.matcher(sql);
		if (matcher != null)  {
			sql = matcher.replaceAll(" ");
		}
		
		if (paraObj == null || paraMapList == null || paraMapList.isEmpty()) {
			return sql;
		}
		
		
		return sql;
	}




	public Object plugin(Object arg0) {
		System.out.println("========In costPlugin " + arg0.getClass().getName());
		return Plugin.wrap(arg0, this);
	}


	public void setProperties(Properties arg0) {
		if (this.slowSqlThreshold <= 0) {
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
