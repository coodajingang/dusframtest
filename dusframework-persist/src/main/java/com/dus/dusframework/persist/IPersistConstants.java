package com.dus.dusframework.persist;

public interface IPersistConstants {

	// 慢sql的默认阈值为 300ms
	final long SLOW_SQL_DEFAULT_THRESHOLD = 300;
	
	// mybatis 的mappid 应用分页的后缀  
	final String PAGINGSQLID_SUFIX = "ByPage";
}
