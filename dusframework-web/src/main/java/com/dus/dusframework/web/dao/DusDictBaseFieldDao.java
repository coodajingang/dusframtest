package com.dus.dusframework.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.dus.dusframework.persist.dao.BaseDao;
import com.dus.dusframework.web.domain.DusDictBaseFieldDo;

@Component
public class DusDictBaseFieldDao extends BaseDao<DusDictBaseFieldDo>{ 

	/**
	 * 查询字典基本字段表， 模糊查询  ； 
	 * 根据中文名称、英文名称、字段缩写分别模糊匹配 
	 * 
	 * @param str
	 * @return
	 */
	public List<DusDictBaseFieldDo> searchLike(String str) {
		return this.selectList(this.getNamespace() + ".searchLike", str);
	}
	
	/**
	 * 查询字典基本字段表， 模糊查询  ； 
	 * 根据中文名称、英文名称、字段缩写分别模糊匹配
	 * @param str
	 * @return
	 */
	public List<DusDictBaseFieldDo> searchLikeByPage(String str) {
		Map<String ,String> map = new HashMap<String ,String>();
		map.put("str", str);
		return this.selectList(this.getNamespace() + ".searchLikeByPage", map, true);
	}

	/**
	 * 根据基本字段的 中、英 、缩写 查询是否存在重复
	 * @param inbean
	 * @return
	 */
	public List<DusDictBaseFieldDo> checkRepeat(DusDictBaseFieldDo inbean) {
		return this.selectList(this.getNamespace() + ".checkRepeat", inbean, true);
	}
}
