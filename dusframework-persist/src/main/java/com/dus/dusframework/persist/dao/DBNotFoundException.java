package com.dus.dusframework.persist.dao;

import com.dus.dusframework.common.CommonRuntimeException;

/**
 * 表述查询为空的异常
 * @author ThinkPad
 *
 */
public class DBNotFoundException extends CommonRuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6488732074745461632L;

	public DBNotFoundException(String message) {
		super("TDB999999", message);
	}
	
	public DBNotFoundException(String message, Throwable cause) {
		super("TDB999999", cause, message);
	}
}
