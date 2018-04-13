package com.dus.dusframework.service;

import java.util.List;

import com.dus.simulate.domain.AppLogFilterInfoDo;

public interface IAppLogFilterService {

	AppLogFilterInfoDo query(String id);

	List<AppLogFilterInfoDo> queryAll();

	void insert(AppLogFilterInfoDo mdo);

	void addRecord();
	
	void doNewAddRecords(AppLogFilterInfoDo mdo);

}