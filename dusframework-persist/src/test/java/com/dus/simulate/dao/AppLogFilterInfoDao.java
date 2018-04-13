package com.dus.simulate.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dus.dusframework.persist.dao.BaseDao;
import com.dus.simulate.domain.AppLogFilterInfoDo;

@Repository
public class AppLogFilterInfoDao extends BaseDao<AppLogFilterInfoDo>{

	public AppLogFilterInfoDo getbyId(String id) {
		System.out.println("In getbyId ....");
		AppLogFilterInfoDo resdo = null;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		System.out.println(this.getNamespace()); 
		try {
			resdo = this.selectByPk(map);
		} catch (Exception ex) {
			System.out.println("Exception:!" + ex);
			ex.printStackTrace();
		}
		
		return resdo;
		
	}

}
