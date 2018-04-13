package com.dus.dusframework.context.page;

import com.dus.dusframework.context.IContextConstants;

/**
 * 分页信息 
 * @author lenovo
 * 分页时， 前端请求送来的有： 请求的页数 ， 每页记录数  ， 总页数 （总页数可以不送）  
 * 响应时 ： 记录信息 ， 当前页数， 每页记录数 ，总页数  ；
 * 
 * 前端上送请求页数(1-N) ，每页记录数（ 必须上送 ）  ， 服务处理时，查询总条数 ，根据当前每页记录数计算总页数； 
 * 然后按照请求页数和每页记录数计算 开始记录数 用于分页查询 。 
 */
public class Page {
	
	// 总记录条数 
	private long totalRecords;
	
	// 当前开始记录条数 
	private long startRecords;
	
	// 每页记录条数 
	private long pageSize;
	
	// 总页数 
	private long totalPage;
	
	// 当前页数 ； 
	private long startPage;
	
	private boolean needPage;
	
	public Page(long startPage, long pageSize) {
		
		if (startPage < 1) {
			this.needPage = false;
			return;
		} 
		this.needPage = true;
		this.startPage = startPage;
		
		if (pageSize <= 0) {
			this.pageSize = IContextConstants.PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
		
		this.startRecords = (this.startPage -1) * this.pageSize ;
	}
	
	public Page() {
		this.needPage = false;
	}

	public void calcuTotalPages(long totalRecords) {
		this.totalRecords = totalRecords;
		
		if (this.totalRecords == 0) {
			this.totalPage = 0;
		}
		
		double totoal = (double)(this.totalRecords);
		double size = (double)this.pageSize;
		
		this.totalPage = (long) Math.ceil(totoal / size);
		
	}
	
	
	@Override
	public String toString() {
		return "Page [totalRecords=" + totalRecords + ", startRecords=" + startRecords + ", pageSize=" + pageSize
				+ ", totalPage=" + totalPage + ", startPage=" + startPage + ", needPage=" + needPage + "]";
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
		calcuTotalPages(totalRecords);
	}

	public long getStartRecords() {
		return startRecords;
	}

	public void setStartRecords(long startRecords) {
		this.startRecords = startRecords;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getStartPage() {
		
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
		this.startRecords = (this.startPage -1) * this.pageSize ;
	}

	public boolean isNeedPage() {
		return needPage;
	}

	public void setNeedPage(boolean needPage) {
		this.needPage = needPage;
	}
	
	
}
