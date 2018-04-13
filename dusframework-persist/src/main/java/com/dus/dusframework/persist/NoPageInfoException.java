package com.dus.dusframework.persist;

import org.springframework.dao.DataAccessException;

public class NoPageInfoException extends DataAccessException{

	public NoPageInfoException(String msg) {
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3151086479870326279L;

}
