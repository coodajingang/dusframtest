package com.dus.dusframework.context;

import com.dus.dusframework.context.page.Page;

public class RunContextUtil {


	private static  ThreadLocal<RunContext> runContext = new ThreadLocal<RunContext>();
	
	/**
	 * 初始会环境上下文 
	 * @param oriMsg
	 * @return
	 */
	public static RunContext createRunContext() {
		if (runContext.get() != null ) {
			runContext.remove();
		}
		
		runContext.set(new RunContext());
		
		return runContext.get();
	}
	
	
	public static void destoryRunContext() {
		runContext.remove();
	}
	
	
	/**
	 * 返回当前线程的上下文
	 * @return
	 */
	public static RunContext getCurrentRunContext() {
		return (runContext.get() != null)? runContext.get() : createRunContext();
	}

	/**
	 * 返回交易码 
	 * @return
	 */
	public static String getTxnCode() {
		
		return getValue(IContextConstants.CONTEXT_TXNCODE, String.class);
	}
	public static void setTxnCode(String code) {
		setValue(IContextConstants.CONTEXT_TXNCODE, code);
	}

	/**
	 * 流水号  
	 * @return
	 */
	public static String getPlatFlowNo() {
		
		return getValue(IContextConstants.CONTEXT_TRANSFLOWNO, String.class);
	}
	public static void setPlatFlowNo(String transflowno) {
		setValue(IContextConstants.CONTEXT_TRANSFLOWNO, transflowno);
	}
	
	/**
	 * 平台日期   
	 * @return
	 */
	public static String getPlatDate() {
		
		return getValue(IContextConstants.CONTEXT_PLATDATE, String.class);
	}
	public static void setPlatDate(String date) {
		setValue(IContextConstants.CONTEXT_PLATDATE, date);
	}
	/**
	 * 平台时间
	 * @return
	 */
	public static String getPlatTime() {
		
		return getValue(IContextConstants.CONTEXT_PLATTIME, String.class);
	}
	public static void setPlatTime(String time) {
		setValue(IContextConstants.CONTEXT_PLATTIME, time);
	}
	
	/**
	 * 响应码
	 * @return
	 */
	public static String getResponseCode() {
		
		return getValue(IContextConstants.CONTEXT_RESPONSECODE, String.class);
	}
	public static void setResponseCode(String code) {
		setValue(IContextConstants.CONTEXT_RESPONSECODE, code);
	}
	
	/**
	 * 响应信息 
	 * @return
	 */
	public static String getResponseInfo() {
		
		return getValue(IContextConstants.CONTEXT_RESPONSEINFO, String.class);
	}
	public static void setResponseInfo(String info) {
		setValue(IContextConstants.CONTEXT_RESPONSEINFO, info);
	}
	/**
	 * 请求报文信息   
	 * @return
	 */
	public static <T> T getRequMsg(Class<T> clazz) {
		
		return getValue(IContextConstants.CONTEXT_REQU_MSG, clazz);
	}
	public static void setRequMsg(Object obj) {
		setValue(IContextConstants.CONTEXT_REQU_MSG, obj);
	}
	
	/**
	 * 请求EndPoint信息   
	 * @return
	 */
	public static <T> T getEndPoint(Class<T> clazz) {
		
		return getValue(IContextConstants.CONTEXT_ENDPOINT, clazz);
	}
	public static void setEndPoint(Object obj) {
		setValue(IContextConstants.CONTEXT_ENDPOINT, obj);
	}
	
	/**
	 * 请求头信息   
	 * @return
	 */
	public static <T> T getHeadInfo(Class<T> clazz) {
		
		return getValue(IContextConstants.CONTEXT_REQ_HEANDINFO, clazz);
	}
	public static void setHeadInfo(Object obj) {
		setValue(IContextConstants.CONTEXT_REQ_HEANDINFO, obj);
	}
	
	/**
	 * 验签头信息   
	 * @return
	 */
	public static <T> T getSignInfo(Class<T> clazz) {
		
		return getValue(IContextConstants.CONTEXT_REQ_SIGNINFO, clazz);
	}
	public static void setSignInfo(Object obj) {
		setValue(IContextConstants.CONTEXT_REQ_SIGNINFO, obj);
	}
	
	/**
	 * 报文体信息   
	 * @return
	 */
	public static <T> T getBodyInfo(Class<T> clazz) {
		
		return getValue(IContextConstants.CONTEXT_REQ_BODYINFO, clazz);
	}
	public static void setBodyInfo(Object obj) {
		setValue(IContextConstants.CONTEXT_REQ_BODYINFO, obj);
	}
	
	/**
	 * 异常信息 信息   
	 * @return
	 */
	public static <T> T getCommonRuntimeException(Class<T> clazz) {
		
		return getValue(IContextConstants.CONTEXT_COMMONRUNTIME, clazz);
	}
	public static void setCommonRuntimeException(Object obj) {
		setValue(IContextConstants.CONTEXT_COMMONRUNTIME, obj);
	}
	
	/**
	 * 报文体对象实例  
	 * @return
	 */
	public static <T> T getBodyObject(Class<T> clazz) {
		
		return getValue(IContextConstants.CONTEXT_REQ_BODYOBJECT, clazz);
	}
	public static void setBodyObject(Object obj) {
		setValue(IContextConstants.CONTEXT_REQ_BODYOBJECT, obj);
	}
	
	/**
	 * 分页信息   
	 * @return
	 */
	public static Page getPageInfo() {
		
		return getValue(IContextConstants.CONTEXT_PAGEINFO, Page.class);
	}
	public static void setPageInfo(Page pageInfo) {
		setValue(IContextConstants.CONTEXT_PAGEINFO, pageInfo);
	}
	
	/**
	 * 根据开始页码 和 每页数量 创建page ，置需要分页标志为true ； 并设置到上下文中；<br>
	 * 等同于 ：  setPageInfo(Page pageInfo) and setNeedPage(true)
	 * @param startPage
	 * @param pageSize
	 */
	public static void setPageInfo(int startPage, int pageSize) {
		Page pageInfo = new Page(startPage, pageSize);
		
		setValue(IContextConstants.CONTEXT_PAGEINFO, pageInfo);
		setValue(IContextConstants.CONTEXT_NEEDPAGE, true);
	}
	
	/**
	 * 请求段上送的是否需要分页信息   
	 * @return
	 */
	public static Boolean getNeedPage() {
		
		Boolean bool = getValue(IContextConstants.CONTEXT_NEEDPAGE, Boolean.class);
		
		if (bool == null) {
			return false;
		}
		return bool;
	}
	public static void setNeedPage(boolean needPage) {
		setValue(IContextConstants.CONTEXT_NEEDPAGE, needPage);
	}
	
	public static void setValue(String el, Object obj) {
		RunContext context = runContext.get();
		if (context != null) {
			context.setValue(el, obj);
		}
	}
	
	public static Object getValue(String el) {
		RunContext context = runContext.get();
		if (context == null) {
			return null;
		}
		return context.getValue(el);
	}
	
	public static <T> T getValue(String el, Class<T> clazz) {
		RunContext context = runContext.get();
		if (context == null) {
			return null;
		}
		
		return context.getValue(el, clazz);
	}
}
