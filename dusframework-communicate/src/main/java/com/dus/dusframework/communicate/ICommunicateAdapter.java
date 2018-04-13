package com.dus.dusframework.communicate;

/**
 * 通讯适配接口
 * @author ThinkPad
 *
 */
public interface ICommunicateAdapter<R, T, S extends BaseCommunicateAdapterConfig> {

	S getAdapterConfig();
	void setAdapterConfig(S config);
	
	R doAdapter(T msg);
}
