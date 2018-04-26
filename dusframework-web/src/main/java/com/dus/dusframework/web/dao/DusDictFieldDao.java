package com.dus.dusframework.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dus.dusframework.persist.dao.BaseDao;
import com.dus.dusframework.web.domain.DusDictFieldDo;

@Component
public class DusDictFieldDao extends BaseDao<DusDictFieldDo> { 

	/**
	 * 查询字典 表， 模糊查询  ； 
	 * 根据中文名称、英文名称、字段缩写分别模糊匹配
	 * @param str
	 * @return
	 */
	public List<DusDictFieldDo> searchLikeByPage(String str) {
		Map<String ,String> map = new HashMap<String ,String>();
		map.put("field", str);
		return this.selectList(this.getNamespace() + ".searchLikeByPage", map, true);
	}

	/**
	 * 根据序号更新字典字段 ，传空 ，则更新为空 
	 * @param inbean
	 * @return
	 */
	public int updateDictFieldInfo(DusDictFieldDo inbean) {
		return this.update(this.getNamespace() + ".updateDictFieldInfo", inbean);
	}

	/**
	 * 根据 seqNo chnName engName 删除
	 * @param inbean
	 * @return
	 */
	public int deleteBySeqAndName(DusDictFieldDo inbean) {
		// TODO Auto-generated method stub
		return this.delete(this.getNamespace() + ".deleteBySeqAndName", inbean);
	}
	
}
