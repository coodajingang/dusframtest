package com.dus.dusframework.persist.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {

	/**
	 * 插入记录 
	 * @param t
	 * @return
	 */
	int insert(T t);
	
	/**
	 * 删除 
	 * @param map
	 * @return
	 */
	int delete(Map map);
	
	/**
	 * 删除 按主键
	 * @param map
	 * @return
	 */
	int deleteByPk(T t);
	
	/**
	 * 更新
	 * @param map
	 * @return
	 */
	int update(Map map);
	
	/**
	 * 更新 按主键
	 * @param map
	 * @return
	 */
	int updateByPk(T t);
	
	
	/**
	 * 按主键查询  , 默认不抛出异常 
	 * @param map
	 * @return
	 */
	T selectByPk(Map map);
	
	/**
	 * 分页查询 
	 * @param map
	 * @return
	 */
	List<T> selectByPage(Map map);
	
	/**
	 * 查询列表 
	 * @param map
	 * @return
	 */
	List<T> selectAll(Map map);
	
}
