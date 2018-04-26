package com.dus.dusframework.web.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dus.dusframework.persist.dao.BaseDao;
import com.dus.dusframework.web.domain.DusDictBaseDomainGroupDo;

@Component
public class DusDictBaseDomainGroupDao extends BaseDao<DusDictBaseDomainGroupDo>{

	/**
	 * 查询所有的域组列表
	 * @return
	 */
	public List<DusDictBaseDomainGroupDo> selectAllGroup() {
		return this.selectList(this.getNamespace() + ".selectAllGroup", 1);
	}
}
