package com.dus.dusframework.persist;

import org.springframework.dao.DataAccessException;

public class QueryPageInfoException extends DataAccessException{

	public QueryPageInfoException(String msg) {
		super(msg);
	}
	
	public QueryPageInfoException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3151086479870326279L;

}
