package com.dus.dusframework.web.domain;

import com.dus.dusframework.persist.BaseDo;

/**
 * 字典字段域名表
 * @author ThinkPad
 *
 */
public class DusDictBaseDomainDo extends BaseDo {
	/** 
	*域名称domain_namevarchar2(64)
	**/
	private String domainName;
	/** 
	*域中文名称domain_chn_namevarchar2(64)
	**/
	private String domainChnName;
	/** 
	*业务定义business_definevarchar2(256)
	**/
	private String businessDefine;
	/** 
	*域编号domain_seqnonumber(9)
	**/
	private Long domainSeqno;
	/** 
	*所属域组domain_groupvarchar2(64)
	**/
	private String domainGroup;
	/** 
	*数据类型data_typevarchar2(64)
	**/
	private String dataType;
	/** 
	*数据格式data_formatvarchar2(64)
	**/
	private String dataFormat;
	/** 
	*取值范围data_scopevarchar2(64)
	**/
	private String dataScope;
	/** 
	*java数据类型java_typevarchar2(64)
	**/
	private String javaType;
	/** 
	*oracle数据类型oracle_typevarchar2(64)
	**/
	private String oracleType;
	/** 
	*jdby数据类型jdbc_typevarchar2(64)
	**/
	private String jdbcType;
	/** 
	*备注remarkvarchar2(256)
	**/
	private String remark;
	
	
	
	@Override
	public String toString() {
		return "DusDictBaseDomainDo [domainName=" + domainName + ", domainChnName=" + domainChnName
				+ ", businessDefine=" + businessDefine + ", domainSeqno=" + domainSeqno + ", domainGroup=" + domainGroup
				+ ", dataType=" + dataType + ", dataFormat=" + dataFormat + ", dataScope=" + dataScope + ", javaType="
				+ javaType + ", oracleType=" + oracleType + ", jdbcType=" + jdbcType + ", remark=" + remark + "]";
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDomainChnName() {
		return domainChnName;
	}
	public void setDomainChnName(String domainChnName) {
		this.domainChnName = domainChnName;
	}
	public String getBusinessDefine() {
		return businessDefine;
	}
	public void setBusinessDefine(String businessDefine) {
		this.businessDefine = businessDefine;
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
	public String getDataScope() {
		return dataScope;
	}
	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
