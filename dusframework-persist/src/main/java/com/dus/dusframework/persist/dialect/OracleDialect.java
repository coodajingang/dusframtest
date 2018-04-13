package com.dus.dusframework.persist.dialect;

import org.springframework.stereotype.Component;

@Component("oracleDialect")
public class OracleDialect implements IDialect{

	public OracleDialect() {
		System.out.println("== OracleDialect Constructor");
	}
	
	public String genPageSql(String sql, long offset, long limit) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");

		pagingSelect.append(sql);
		if (offset > 0) {
			pagingSelect.append(" ) row_  where rownum <= ");
			pagingSelect.append(offset + limit);
			pagingSelect.append(" ) where rownum_ > " + offset);
		} else {
			pagingSelect.append(" ) row_ where rownum <= " + limit + " )  ");
		}

		if (isForUpdate)
			pagingSelect.append(" for update");

		return pagingSelect.toString();
	}

	public String genCountSql(String sql) {
		sql = sql.trim();
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
		}
		
		return "select count(*) from (" + sql + ") a";
	}

}
