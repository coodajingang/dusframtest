package com.dus.dusframework.web.domain;

import com.dus.dusframework.persist.BaseDo;

/**
 * 字段 域组 表
 * @author ThinkPad
 *
 */
public class DusDictBaseDomainGroupDo extends BaseDo {

	/** 
	*域组名称domain_groupvarchar2(64)
	**/
	private String domainGroup;
	/** 
	*域组编号domain_group_seqnonumber(9)
	**/
	private Long domainGroupSeqno;
	/** 
	*业务定义business_definevarchar2(256)
	**/
	private String businessDefine;
	/** 
	*备注remarkvarchar2(256)
	**/
	private String remark;
	
	
	
	@Override
	public String toString() {
		return "DusDictBaseDomainGroup [domainGroup=" + domainGroup + ", domainGroupSeqno=" + domainGroupSeqno
				+ ", businessDefine=" + businessDefine + ", remark=" + remark + "]";
	}
	
	public String getDomainGroup() {
		return domainGroup;
	}
	public void setDomainGroup(String domainGroup) {
		this.domainGroup = domainGroup;
	}
	public Long getDomainGroupSeqno() {
		return domainGroupSeqno;
	}
	public void setDomainGroupSeqno(Long domainGroupSeqno) {
		this.domainGroupSeqno = domainGroupSeqno;
	}
	public String getBusinessDefine() {
		return businessDefine;
	}
	public void setBusinessDefine(String businessDefine) {
		this.businessDefine = businessDefine;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
