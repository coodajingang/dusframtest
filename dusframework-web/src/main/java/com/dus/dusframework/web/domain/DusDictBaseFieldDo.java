package com.dus.dusframework.web.domain;

import com.dus.dusframework.persist.BaseDo;

public class DusDictBaseFieldDo extends BaseDo{


	private String chnName;
	private String engName;
	private String abbreviate;
	private String baseType;
	private String source;
	private String remark;
	private String fieldGroup;
	private String begins;
	
	@Override
	public String toString() {
		return "DusDictBaseFieldDo [chnName=" + chnName + ", engName=" + engName + ", abbreviate=" + abbreviate
				+ ", baseType=" + baseType + ", source=" + source + ", remark=" + remark + ", fieldGroup=" + fieldGroup
				+ ", begins=" + begins + "]";
	}

	public String getChnName() {
		return chnName;
	}

	public void setChnName(String chnName) {
		this.chnName = chnName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getAbbreviate() {
		return abbreviate;
	}

	public void setAbbreviate(String abbreviate) {
		this.abbreviate = abbreviate;
	}

	public String getBaseType() {
		return baseType;
	}

	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFieldGroup() {
		return fieldGroup;
	}

	public void setFieldGroup(String fieldGroup) {
		this.fieldGroup = fieldGroup;
	}

	public String getBegins() {
		return begins;
	}

	public void setBegins(String begins) {
		this.begins = begins;
	}

	
}
