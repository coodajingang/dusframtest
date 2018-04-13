package com.dus.dusframework.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
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
import org.apache.ibatis.session.Configuration;

import com.dus.dusframework.persist.dialect.IDialect;

@Intercepts(@Signature(args = {java.sql.Connection.class}, method = "prepare", type = StatementHandler.class))
public class MyInterseptor implements Interceptor{

	private IDialect dialect;
	

	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("================In page intercept");
		long begin = System.currentTimeMillis();
		
		StatementHandler target = (StatementHandler) invocation.getTarget(); 

		MetaObject mobj = SystemMetaObject.forObject(target);
		
		 // 分离代理对象链  
        while (mobj.hasGetter("h")) {  
            Object object = mobj.getValue("h");  
            mobj = SystemMetaObject.forObject(object); 
        }  
		
     // 分离最后一个代理对象的目标类  
        while (mobj.hasGetter("target")) {  
            Object object = mobj.getValue("target");  
            mobj = SystemMetaObject.forObject(object);  
        }
        
        
		//RoutingStatementHandler routingStatementHandler = MetaObject.forObject(mobj, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
			//	SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		
		//StatementHandler statementHandler = (StatementHandler) ReflectClazz.getFieldValue(routingStatementHandler, "delegate");  
        //这个对象封装了SQL语句  
		
		// BoundSql boundSql = routingStatementHandler.getBoundSql();
		
        BoundSql boundSql = (BoundSql) mobj.getValue("delegate.boundSql");
        
		System.out.println("======boundSql:" + boundSql.getSql());
		System.out.println("======boundSql-parameterObject :" + boundSql.getParameterObject());
		List<ParameterMapping> parmList = boundSql.getParameterMappings();
		
		if (parmList != null && parmList.size() > 0) {
			System.out.println("==parameterMapping list 不空！");
			for (ParameterMapping mapp : parmList) {
				System.out.println(mapp.getJavaType().getName() + " " + mapp.getJdbcTypeName() + " " + mapp.getProperty());
			}
		} else {
			System.out.println("====parameterMapping list 为空！");
		}
		
		// 判断是否需要分页 
		System.out.println("需要分页");

//		MetaObject metaObject = MetaObject.forObject(routingStatementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
//				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		
		MetaObject metaObject = mobj;
		// BaseStatementHandler-->mappedStatement(delegate为mappedStatement属性名
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

		Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");

		System.out.println("===" + mappedStatement.getId()); 
		
		String sql  = boundSql.getSql();

		System.out.println("==sql:" + sql);
		
		String countSql = this.dialect.genCountSql(sql);
		
		System.out.println("==countsql:" + countSql);
		
		Connection connection = (Connection) invocation.getArgs()[0];
		
		PreparedStatement countStatement = null;
		ResultSet rs = null;
		
		long totalRecords =  0;
		
		try {
			countStatement = connection.prepareStatement(countSql);

			// 获取原始的sql参数信息
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			// 设置参数值
			parameterHandler.setParameters(countStatement);
			// 执行获取总条数
			rs = countStatement.executeQuery();
			
			if (rs != null && rs.next()) {
				totalRecords = rs.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println(" 分页查询countsql异常" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (countStatement != null) {
					countStatement.close();
				}
			} catch (SQLException e) {
				System.out.println(" 分页查询countsql异常" + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException();
			}
		}


		if (totalRecords == 0 ){
			System.out.println("总条数为0 ， 不分页 ");
			return null;
		}
		
		System.out.println("总条数：" + totalRecords);
		
		// 构造分页sql 
		String pageSql = this.dialect.genPageSql(sql, 1, 2);

		System.out.println("pageSql:" + pageSql);
		
		metaObject.setValue("delegate.boundSql.sql", pageSql);
		
		Object obj =  invocation.proceed();
		
		System.out.println("sql执行结束： 耗时：" + (System.currentTimeMillis() - begin));
		
		return obj;
	}

    

	public Object plugin(Object target) {
		
		System.out.println("================In Page plugin");
		System.out.println("==================" + target.getClass().getName());
		if (target instanceof RoutingStatementHandler) {
			System.out.println("开始进入拦截器MyInterceptor");
			return Plugin.wrap(target, this);
		}
		
		System.out.println("不进入拦截器");
		return target;
	}

	public void setProperties(Properties arg0) {
		System.out.println("================In setProperties");
		// TODO Auto-generated method stub
		
	}


	public IDialect getDialect() {
		return dialect;
	}


	public void setDialect(IDialect dialect) {
		this.dialect = dialect;
	}

	
	
}
