package com.dus.dusframework.common;

public class InvalidateEnumValueException extends CommonRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidateEnumValueException(String code, Object... params) {
		super(code, params);
	}
}
