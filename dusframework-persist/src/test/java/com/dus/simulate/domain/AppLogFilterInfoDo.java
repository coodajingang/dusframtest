package com.dus.simulate.domain;

import com.dus.dusframework.persist.BaseDo;

public class AppLogFilterInfoDo extends BaseDo{

	// 项目id varchar2(32)
	private String projectId;
	// 项目名称 varchar2(32)
	private String projectName;
	// 系统id varchar2(32)
	private String systemId;
	// 系统名称 varchar2(32)
	private String systemName;
	// 过滤代码 varchar2(256)
	private String filterCode;
	// 过滤类型 varchar2(32)
	private String filterType;
	// 备注1 varchar2(256)
	private String remark1;
	// 备注2 varchar2(256)
	private String remark2;
	
	
	
	
	@Override
	public String toString() {
		return "AppLogFilterInfoDo [projectid=" + projectId + ", projectname=" + projectName + ", systemid=" + systemId
				+ ", systemname=" + systemName + ", filtercode=" + filterCode + ", filtertype=" + filterType
				+ ", remark1=" + remark1 + ", remark2=" + remark2 + ", timestamp=" + this.getTms() + ", vno=" + this.getVno() + "]";
	}




	public String getProjectId() {
		return projectId;
	}




	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}




	public String getProjectName() {
		return projectName;
	}




	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}




	public String getSystemId() {
		return systemId;
	}




	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}




	public String getSystemName() {
		return systemName;
	}




	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}




	public String getFilterCode() {
		return filterCode;
	}




	public void setFilterCode(String filterCode) {
		this.filterCode = filterCode;
	}




	public String getFilterType() {
		return filterType;
	}




	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}




	public String getRemark1() {
		return remark1;
	}




	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}




	public String getRemark2() {
		return remark2;
	}




	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	
	
}
