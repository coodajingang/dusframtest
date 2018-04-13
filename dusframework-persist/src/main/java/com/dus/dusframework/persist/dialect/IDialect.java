package com.dus.dusframework.persist.dialect;

public interface IDialect {

	String genPageSql(String srcsql, long offset, long limit);
	
	String genCountSql(String srcsql);
}
