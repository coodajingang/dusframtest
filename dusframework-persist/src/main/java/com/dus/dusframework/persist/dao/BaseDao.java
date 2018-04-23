package com.dus.dusframework.persist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dus.dusframework.common.util.StringUtils;
import com.dus.dusframework.persist.NoPageInfoException;
import com.dus.dusframework.persist.PageMybatisInterceptor;

public abstract class BaseDao <T> extends SqlSessionDaoSupport implements IBaseDao<T>{

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(BaseDao.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	private String namespace;
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		System.out.println("sqlSessionFactory Autowired.........");
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public String getNamespace() {
		return StringUtils.isNotEmpty(this.namespace) ? this.namespace : this.getClass().getName();
	}
	
	public int insert(T t) {
		return this.insert(this.getNamespace() + ".insert", t);
	}

	private int insert(String id, Object t) {
		try {
			return getSqlSession().insert(id, t);
		} catch (Exception exp) {
			log.error("数据库insert操作:" + id + "异常!" , exp);
			throw new DBRuntimeException("数据库insert操作:" + id + "异常!" , exp);
		}
	}
	
	public int delete(Map map) {
		// TODO Auto-generated method stub
		return this.delete(this.getNamespace() + ".delete", map);
	}

	/**
	 * 删除 按主键
	 * @param map
	 * @return
	 */
	public int deleteByPk(T t) {
		return this.delete(this.getNamespace() + ".deleteByPk", t);
	}
	
	private int delete(String id, Object t) {
		try {
			return getSqlSession().delete(id, t);
		} catch (Exception exp) {
			log.error("数据库delete操作:" + id + "异常!" , exp);
			throw new DBRuntimeException("数据库delete操作:" + id + "异常!" , exp);
		}
	}
	
	public int update(Map map) {
		// TODO Auto-generated method stub
		return this.update(this.getNamespace() + ".update", map);
	}

	/**
	 * 更新 按主键
	 * @param map
	 * @return
	 */
	public int updateByPk(T t) {
		return this.update(this.getNamespace() + ".updateByPk", t);
	}
	
	private int update(String id, Object t) {
		try {
			return getSqlSession().update(id, t);
		} catch (Exception exp) {
			log.error("数据库update操作:" + id + "异常!" , exp);
			throw new DBRuntimeException("数据库update操作:" + id + "异常!" , exp);
		}
	}
	
	/**
	 * 按主键查询  ， 查询为空时不抛出异常  ！
	 */
	public T selectByPk(Map map) {
		//System.out.println("sqlID=" + this.getNamespace() + ".selectByPk");
		return this.selectByPk(map, false);
	}

	/**
	 * 按主键查询  ， 查询为空时按需要抛出异常  ！
	 */
	public T selectByPk(Map map, boolean expWhenNoData) {
		//System.out.println("sqlID=" + this.getNamespace() + ".selectByPk");
		return this.selectOne(this.getNamespace() + ".selectByPk", map, expWhenNoData);
	}
	
	private T selectOne(String id, Object t, boolean expWhenNoData) {
		T res = null;
		try {
			res = getSqlSession().selectOne(id, t);
		} catch (Exception exp) {
			log.error("数据库selectOne操作:" + id + "异常!" , exp);
			throw new DBRuntimeException("数据库selectOne操作:" + id + "异常!" , exp);
		}
		
		// 查询为空 且 查询为空时需要抛出异常 
		if (res == null && expWhenNoData) {
			log.error("数据库selectOne操作:" + id + "查询为空!");
			throw new DBNotFoundException("数据库selectOne操作:" + id + "查询为空!");
		}
		return res;
	}
	
	
	/**
	 * 分页查询   ， 查询空 不抛出异常 ， 直接返回 
	 */
	public List<T> selectByPage(Map map) {
		// TODO Auto-generated method stub
		return this.selectList(this.getNamespace() + ".selectByPage", map);
	}

	/**
	 * 查询列表 , 不分页  
	 * @param map
	 * @return
	 */
	public List<T> selectAll(Map map) {
		return this.selectList(this.getNamespace() + ".selectAll", map);
	}
	
	public List<T> selectList(String id, Object t) {
		try {
			return getSqlSession().selectList(id, t);
		} catch (NoPageInfoException noexp) {
			log.error("数据库selectList操作:" + id + "异常!缺少分页信息！" , noexp);
			throw noexp;
		} catch (Exception exp) {
			log.error("数据库selectList操作:" + id + "异常!" , exp);
			throw new DBRuntimeException("数据库selectList操作:" + id + "异常!" , exp.getMessage());
		}
		
	}
	
	/**
	 * 查询列表 ， 当查询为空时不抛出异常  ， 返回null
	 * <br>nothrowexp 为true时， 查询空不抛异常 ，返回null'
	 * @param id
	 * @param t
	 * @param throwexp
	 * @return
	 */
	public List<T> selectList(String id, Object t, boolean nothrowexp) {
		try {
			return getSqlSession().selectList(id, t);
		} catch (NoPageInfoException noexp) {
			log.error("数据库selectList操作:" + id + "异常!缺少分页信息！" , noexp);
			throw noexp;
		} catch (Exception exp) {
			log.debug("异常类型：" + (exp instanceof DBNotFoundException));
			log.debug(exp.getClass().getName());
			if (exp instanceof DBNotFoundException && nothrowexp) {
				log.info("查询结果为空！");
				return null;
			} else {
				log.error("数据库selectList操作:" + id + "异常!" , exp);
				throw new DBRuntimeException("数据库selectList操作:" + id + "异常!" , exp.getMessage());
			}
		}
		
	}
}
