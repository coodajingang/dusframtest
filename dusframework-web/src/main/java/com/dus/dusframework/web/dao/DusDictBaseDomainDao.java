package com.dus.dusframework.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dus.dusframework.persist.dao.BaseDao;
import com.dus.dusframework.web.domain.DusDictBaseDomainDo;

@Component
public class DusDictBaseDomainDao  extends BaseDao<DusDictBaseDomainDo>{

	public List<DusDictBaseDomainDo> selectByDomainGroup(DusDictBaseDomainDo in) {
		return this.selectList(this.getNamespace() + ".selectByDomainGroup", in);
	}

	public List<DusDictBaseDomainDo> searchLikeByPage(String field) {
		Map<String ,String> map = new HashMap<String ,String>();
		map.put("field", field);
		return this.selectList(this.getNamespace() + ".searchLikeByPage", map, true);
	}
}
