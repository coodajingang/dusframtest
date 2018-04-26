package com.dus.dusframework.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dus.dusframework.persist.dao.BaseDao;
import com.dus.dusframework.web.domain.DusDictBaseCodeDo;

@Component
public class DusDictBaseCodeDao extends BaseDao<DusDictBaseCodeDo>{

	
	public List<DusDictBaseCodeDo> searchLikeByPage(String field) {
		Map<String ,String> map = new HashMap<String ,String>();
		map.put("field", field);
		return this.selectList(this.getNamespace() + ".searchLikeByPage", map, true);
	}

	public int updateCodeInfo(DusDictBaseCodeDo inbean) {
		
		return this.update(this.getNamespace() + ".updateCodeInfo", inbean);
	}

	/**
	 * 根据domainSeqno 、domainName 删除 所有的代码值； 若有输入code ，则删除特定的code  
	 * @param inbean
	 * @return
	 */
	public int deleteByDomainCode(DusDictBaseCodeDo inbean) {
		
		return this.delete(this.getNamespace() + ".deleteByDomainCode", inbean);
	}

}
