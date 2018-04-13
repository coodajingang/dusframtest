package com.dus.dusframework.persist;

import java.sql.Timestamp;

import com.dus.dusframework.common.util.DateUtils;

public abstract class BaseDo {
	/** channelCgy 交易发起渠道类别 */
	private String channelCgy;
	/** channelCode 交易发起渠道编号 */
	private String channelCode;
	/** multiEntity 多实体标识 */
	private String multiEntity;
	/** busiDate 营业日期 */
	private String busiDate;
	/** version 字段 */
	private Integer vno;
	/** tms 时间戳 */
	private Timestamp tms;
	/** dac 校验字段 */
	private String dacVerf;
	
	public BaseDo() {
		this.dacVerf = "AAAAAAAAAA";
		this.tms = new Timestamp(System.currentTimeMillis());
		this.vno = 1;
		this.busiDate = DateUtils.getDate8Str(DateUtils.getBizDate());
		this.multiEntity = "00001";
		this.channelCgy = "99";
		this.channelCode = "999";
	}
	
	public abstract String toString();
	
	public String getChannelCgy() {
		return channelCgy;
	}
	public void setChannelCgy(String channelCgy) {
		this.channelCgy = channelCgy;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getMultiEntity() {
		return multiEntity;
	}
	public void setMultiEntity(String multiEntity) {
		this.multiEntity = multiEntity;
	}
	public String getBusiDate() {
		return busiDate;
	}
	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}
	public Integer getVno() {
		return vno;
	}
	public void setVno(Integer vno) {
		this.vno = vno;
	}
	public String getDacVerf() {
		return dacVerf;
	}
	public void setDacVerf(String dacVerf) {
		this.dacVerf = dacVerf;
	}

	public Timestamp getTms() {
		return tms;
	}

	public void setTms(Timestamp tms) {
		this.tms = tms;
	}

	
	
}
