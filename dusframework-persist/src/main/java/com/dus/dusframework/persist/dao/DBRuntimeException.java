package com.dus.dusframework.persist.dao;

import com.dus.dusframework.common.CommonRuntimeException;

/**
 * 数据库异常时异常  
 * @author ThinkPad
 *
 */
public class DBRuntimeException extends CommonRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4082519582173329971L;

	public DBRuntimeException(String message) {
		super("TDB100001", message);
	}
	
	public DBRuntimeException(String message, Throwable cause) {
		super("TDB100001", cause, message);
	}
	
	public DBRuntimeException(String code ,String message) {
		super(code, message);
	}
	
	public DBRuntimeException(String code ,String message, Throwable cause) {
		super(code, cause, message);
	}
}
