package com.dus.dusframework.web.domain;

import com.dus.dusframework.persist.BaseDo;

public class DusDictBaseCodeDo extends BaseDo {
	/** 
	*域编号domain_seqnonumber(9)
	**/
	private Long domainSeqno;
	/** 
	*域名称domain_namevarchar2(64)
	**/
	private String domainName;
	/** 
	*代码取值codevarchar2(8)
	**/
	private String code;
	/** 
	*代码中文名称code_namevarchar2(64)
	**/
	private String codeName;
	/** 
	*代码英文名称code_englishvarchar2(64)
	**/
	private String codeEnglish;
	/** 
	*代码业务含义business_definevarchar2(256)
	**/
	private String businessDefine;
	/** 
	*java数据类型java_typevarchar2(64)
	**/
	private String javaType;
	/** 
	*java命名规范java_namevarchar2(64)
	**/
	private String javaName;
	/** 
	*备注remarkvarchar2(256)
	**/
	private String remark;
	
	/**
	 * 操作类型字段：
	 * 01-新增代码值
	 * 02-新增带域的代码域值 
	 * 03-更新 
	 * 04-删除
	 */
	private String operator;
	
	/**
	 * 原代码值 ， 用于更新操作 
	 */
	private String oriCode;
	
	@Override
	public String toString() {
		return "DusDictBaseCodeDo [domainSeqno=" + domainSeqno + ", domainName=" + domainName + ", code=" + code
				+ ", codeName=" + codeName + ", codeEnglish=" + codeEnglish + ", businessDefine=" + businessDefine
				+ ", javaType=" + javaType + ", javaName=" + javaName + ", remark=" + remark + ", operator=" + operator
				+ ", oriCode=" + oriCode + "]";
	}
	public Long getDomainSeqno() {
		return domainSeqno;
	}
	public void setDomainSeqno(Long domainSeqno) {
		this.domainSeqno = domainSeqno;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeEnglish() {
		return codeEnglish;
	}
	public void setCodeEnglish(String codeEnglish) {
		this.codeEnglish = codeEnglish;
	}
	public String getBusinessDefine() {
		return businessDefine;
	}
	public void setBusinessDefine(String businessDefine) {
		this.businessDefine = businessDefine;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOriCode() {
		return oriCode;
	}
	public void setOriCode(String oriCode) {
		this.oriCode = oriCode;
	}

	
	
}
