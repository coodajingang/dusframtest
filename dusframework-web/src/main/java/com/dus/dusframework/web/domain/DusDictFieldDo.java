package com.dus.dusframework.web.domain;

import com.dus.dusframework.persist.BaseDo;

public class DusDictFieldDo extends BaseDo {
	/** 
	*序号seq_nonumber(9)
	**/
	private Long seqNo;
	/** 
	*英文简称eng_namevarchar2(64)
	**/
	private String engName;
	/** 
	*中文名称chn_namevarchar2(64)
	**/
	private String chnName;
	/** 
	*英文名称eng_full_namevarchar2(64)
	**/
	private String engFullName;
	/** 
	*业务定义business_definevarchar2(256)
	**/
	private String businessDefine;
	/** 
	*来源sourcevarchar2(64)
	**/
	private String source;
	/** 
	*数据类别data_typevarchar2(32)
	**/
	private String dataType;
	/** 
	*数据格式data_formatvarchar2(32)
	**/
	private String dataFormat;
	/** 
	*数据域domain_namevarchar2(32)
	**/
	private String domainName;
	/** 
	*数据域序号domain_seqnonumber(9)
	**/
	private Long domainSeqno;
	/** 
	*数据域组domain_groupvarchar2(32)
	**/
	private String domainGroup;
	/** 
	*java数据类型java_typevarchar2(32)
	**/
	private String javaType;
	/** 
	*java命名规范java_namevarchar2(32)
	**/
	private String javaName;
	/** 
	*oracle数据类型oracle_typevarchar2(32)
	**/
	private String oracleType;
	/** 
	*jdbc数据类型jdbc_typevarchar2(32)
	**/
	private String jdbcType;
	/** 
	*提交人submittervarchar2(32)
	**/
	private String submitter;
	/** 
	*提交时间submit_timevarchar2(32)
	**/
	private String submitTime;
	/** 
	*审核人confirmervarchar2(32)
	**/
	private String confirmer;
	/** 
	*审核时间confirm_timevarchar2(32)
	**/
	private String confirmTime;
	/** 
	*备注remarkvarchar2(32)
	**/
	private String remark;
	public Long getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getChnName() {
		return chnName;
	}
	public void setChnName(String chnName) {
		this.chnName = chnName;
	}
	public String getEngFullName() {
		return engFullName;
	}
	public void setEngFullName(String engFullName) {
		this.engFullName = engFullName;
	}
	public String getBusinessDefine() {
		return businessDefine;
	}
	public void setBusinessDefine(String businessDefine) {
		this.businessDefine = businessDefine;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataFormat() {
		return dataFormat;
	}
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public Long getDomainSeqno() {
		return domainSeqno;
	}
	public void setDomainSeqno(Long domainSeqno) {
		this.domainSeqno = domainSeqno;
	}
	public String getDomainGroup() {
		return domainGroup;
	}
	public void setDomainGroup(String domainGroup) {
		this.domainGroup = domainGroup;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getJavaName() {
		return javaName;
	}
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	public String getOracleType() {
		return oracleType;
	}
	public void setOracleType(String oracleType) {
		this.oracleType = oracleType;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getConfirmer() {
		return confirmer;
	}
	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
	}
	public String getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "DusDictFieldDo [seqNo=" + seqNo + ", engName=" + engName + ", chnName=" + chnName + ", engFullName="
				+ engFullName + ", businessDefine=" + businessDefine + ", source=" + source + ", dataType=" + dataType
				+ ", dataFormat=" + dataFormat + ", domainName=" + domainName + ", domainSeqno=" + domainSeqno
				+ ", domainGroup=" + domainGroup + ", javaType=" + javaType + ", javaName=" + javaName + ", oracleType="
				+ oracleType + ", jdbcType=" + jdbcType + ", submitter=" + submitter + ", submitTime=" + submitTime
				+ ", confirmer=" + confirmer + ", confirmTime=" + confirmTime + ", remark=" + remark + "]";
	}

	
}
