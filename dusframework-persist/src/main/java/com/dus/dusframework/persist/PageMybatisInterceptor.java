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
import org.slf4j.Logger;

import com.dus.dusframework.common.CommonRuntimeException;
import com.dus.dusframework.common.util.ComUtils;
import com.dus.dusframework.context.RunContextUtil;
import com.dus.dusframework.context.page.Page;
import com.dus.dusframework.persist.dao.DBNotFoundException;
import com.dus.dusframework.persist.dialect.IDialect;

/**
 * mybatis 拦截器， 拦截StatementHandler.class  用于查询分页 ；
 * @author lenovo
 *
 */
@Intercepts(@Signature(args = {java.sql.Connection.class, Integer.class}, method = "prepare", type = StatementHandler.class))
public class PageMybatisInterceptor implements Interceptor{
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(PageMybatisInterceptor.class);
	private IDialect dialect;
	
	public Object intercept(Invocation invocation) throws Throwable {
		boolean needPage = false;
		log.debug("分页拦截器开始处理");
		
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
        
        MappedStatement mappedStatement = (MappedStatement) mobj.getValue("delegate.mappedStatement");
        
        String mapid = mappedStatement.getId();
        
        log.debug("判断是否需要分页：" + mapid);
        
        if (isMappIdMatched(mapid) && RunContextUtil.getNeedPage()) {
        	Page pageInfo = RunContextUtil.getPageInfo();
        	
        	if (pageInfo == null ) {
        		log.error("未上送相关分页信息");
        		throw new NoPageInfoException("没有分页参数信息 ");
        	}
        	
        	// 进行分页操作  
        	BoundSql boundSql = (BoundSql) mobj.getValue("delegate.boundSql");
        	String sql  = boundSql.getSql();

    		String countSql = this.dialect.genCountSql(sql);

    		log.debug("CountSql：" + countSql);
    		
    		Connection connection = (Connection) invocation.getArgs()[0];
    		
    		PreparedStatement countStatement = null;
    		ResultSet rs = null;
    		
    		long totalRecords =  0;
    		
    		try {
    			countStatement = connection.prepareStatement(countSql);

    			// 获取原始的sql参数信息
    			ParameterHandler parameterHandler = (ParameterHandler) mobj.getValue("delegate.parameterHandler");
    			// 设置参数值
    			parameterHandler.setParameters(countStatement);
    			// 执行获取总条数
    			rs = countStatement.executeQuery();
    			
    			if (rs != null && rs.next()) {
    				totalRecords = rs.getLong(1);
    			}
    		} catch (SQLException e) {
    			log.error(" 分页查询countsql异常" + e.getMessage());
    			e.printStackTrace();
    			throw new QueryPageInfoException("分页查询异常" + e.getMessage() , e);
    		} finally {
    			try {
    				if (rs != null) {
    					rs.close();
    				}
    				if (countStatement != null) {
    					countStatement.close();
    				}
    			} catch (SQLException e) {
    				log.error(" 分页查询countsql异常" + e.getMessage());
    				e.printStackTrace();
    				throw new QueryPageInfoException("分页查询异常" + e.getMessage() , e);
    			}
    		}


    		// 设置到pageInfo中  ；
    		pageInfo.setTotalRecords(totalRecords);

    		log.debug("分页信息：" + pageInfo.toString());
    		
    		/**
    		if (pageInfo.getStartPage() > pageInfo.getTotalPage()) {
    			log.info("当前请求页码大于总页码，直接返回空！");
    			throw new DBNotFoundException("请求页码大于总页码，查询为空！");
    		}
    		**/
    		// 构造分页sql 
    		String pageSql = this.dialect.genPageSql(sql, pageInfo.getStartRecords(), pageInfo.getPageSize());

    		log.debug("pageSql:" + pageSql);
    		
    		mobj.setValue("delegate.boundSql.sql", pageSql);
        } else {
        	log.debug("无需分页");
        }
        
		return invocation.proceed();
	}

	
	private void aa (Invocation invocation) throws Throwable {
		boolean needPage = false;
		log.info("分页拦截器开始处理");
		
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
        
        MappedStatement mappedStatement = (MappedStatement) mobj.getValue("delegate.mappedStatement");
        
        String mapid = mappedStatement.getId();
        
        log.debug("判断是否需要分页：" + mapid);
        
        if (isMappIdMatched(mapid) && RunContextUtil.getNeedPage()) {
        	Page pageInfo = RunContextUtil.getPageInfo();
        	
        	if (!pageInfo.isNeedPage()) {
        		log.error("未上送相关分页信息：" + pageInfo.toString());
        		throw new NoPageInfoException("没有分页参数信息 " + pageInfo.toString());
        	}
        	
        	// 进行分页操作  
        	
        }
        
        
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
		 mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

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
			throw new CommonRuntimeException();
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
				throw new CommonRuntimeException();
			}
		}


		if (totalRecords == 0 ){
			System.out.println("总条数为0 ， 不分页 ");
			return ;
		}
		
		System.out.println("总条数：" + totalRecords);
		
		// 构造分页sql 
		String pageSql = this.dialect.genPageSql(sql, 1, 2);

		System.out.println("pageSql:" + pageSql);
		
		metaObject.setValue("delegate.boundSql.sql", pageSql);
		
		Object obj =  invocation.proceed();
		
		System.out.println("sql执行结束： 耗时：" + (System.currentTimeMillis() - begin));
		
		return ;
	}
	
	private boolean isMappIdMatched(String mapid) {
		return ComUtils.endWith(mapid, IPersistConstants.PAGINGSQLID_SUFIX) || 
				ComUtils.endWith(mapid, "selectAll");
	}
    
	public Object plugin(Object target) {
		
		//System.out.println("================In Page plugin");
		//System.out.println("==================" + target.getClass().getName());
		if (target instanceof RoutingStatementHandler) {
			//System.out.println("开始进入拦截器MyInterceptor");
			//log.debug("开始进入分页拦截器");
			return Plugin.wrap(target, this);
		}
		
		//log.debug("跳过分页拦截器");
		//System.out.println("不进入拦截器");
		return target;
	}


	public void setProperties(Properties arg0) {
		//System.out.println("================In setProperties");
		// TODO Auto-generated method stub
		
	}


	public IDialect getDialect() {
		return dialect;
	}


	public void setDialect(IDialect dialect) {
		this.dialect = dialect;
	}

	
}
