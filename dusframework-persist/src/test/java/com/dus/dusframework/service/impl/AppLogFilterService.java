package com.dus.dusframework.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dus.dusframework.common.CommonRuntimeException;
import com.dus.dusframework.service.IAppLogFilterService;
import com.dus.simulate.dao.AppLogFilterInfoDao;
import com.dus.simulate.domain.AppLogFilterInfoDo;

@Component("appLogFilterService")
public class AppLogFilterService implements IAppLogFilterService {
	
	@Autowired
	private AppLogFilterInfoDao dao;
	
	/* (non-Javadoc)
	 * @see com.dus.dusframe.service.IAppLogFilterService#query(java.lang.String)
	 */
	//@Override
	public AppLogFilterInfoDo query(String id) {
		System.out.println("query ==================================");
		return dao.getbyId(id);
	}
	
	/* (non-Javadoc)
	 * @see com.dus.dusframe.service.IAppLogFilterService#queryAll()
	 */
	//@Override
	public List<AppLogFilterInfoDo> queryAll() {
		System.out.println("query All ==================================");
		//return dao.getAll();
		return null;
	}
	
	public List<AppLogFilterInfoDo> queryAll(String id) {
		System.out.println("query All by page  ==================================");
		//return dao.selectByxxByPage(id);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.dus.dusframe.service.IAppLogFilterService#insert(com.dus.simulate.domain.AppLogFilterInfoDo)
	 */
	//@Override
	public void insert(AppLogFilterInfoDo mdo) {
		//dao.insert(mdo);
		this.doNewAddRecords(mdo);
	}
	
	public void doNewAddRecords(AppLogFilterInfoDo mdo) {
		System.out.println("kaishi =====================");
		
		int res = dao.insert(mdo);
		System.out.println("insert row num :" + res);
		
		throw new CommonRuntimeException();
	}
	
	/* (non-Javadoc)
	 * @see com.dus.dusframe.service.IAppLogFilterService#addRecord()
	 */
	//@Override
	public void addRecord() {
		System.out.println("first step : query");
		AppLogFilterInfoDo mmdo = query("800");
		
		System.out.println(mmdo.toString());
		
		System.out.println("second step : insert");
		String id = ((Math.random()* 10000) % 1000 )+"";
		mmdo.setProjectId(id);
		insert(mmdo);
		
		System.out.println("insert success ! id = " + id);
		
		System.out.println("query the id ");
		mmdo = query(id);
		
		System.out.println("success insert data:" + mmdo.toString());
		
		System.out.println("throw exception!");
		throw new RuntimeException();
	}
}
