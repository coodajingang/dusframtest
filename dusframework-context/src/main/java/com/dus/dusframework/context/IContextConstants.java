package com.dus.dusframework.context;

public interface IContextConstants {

	String CONTEXT_TXNCODE = "['TXNCODE']"; // 交易码
	String CONTEXT_REQU_MSG = "['REQU_MSG']"; // 原请求报文 
	String CONTEXT_RESP_MSG = "['RESP_MSG']"; // 响应报文 
	String CONTEXT_REQ_HEANDINFO = "['REQ_HEANDINFO']"; // 请求头信息 
	String CONTEXT_REQ_SIGNINFO = "['REQ_SIGNINFO']";  // 请求验签信息 
	String CONTEXT_REQ_BODYINFO = "['REQ_BODYINFO']"; // 请求报文体信息 
	
	String CONTEXT_REQ_BODYOBJECT = "['REQ_BODYOBJECT']"; // 请求体对应的对象实例
	
	String CONTEXT_ENDPOINT = "['REQ_ENDPOINT']"; // 请求ENDPOINT 信息
	
	String CONTEXT_TRANSFLOWNO = "['TRANSFLOWNO']"; // 交易流水号  
	
	String CONTEXT_PAGEINFO = "['PAGEINFO']"; // 分页信息 page.class  
	String CONTEXT_NEEDPAGE = "['NEEDPAGE']"; //  是否需要分页 
	
	String CONTEXT_COMMONRUNTIME = "['COMMONRUNTIME']"; //  异常信息  
	
	String  CONTEXT_PLATDATE = "['PLATDATE']"; //  交易日期信息  
	String  CONTEXT_PLATTIME = "['PLATTIME']"; //  交易日期信息  
	
	String  CONTEXT_RESPONSECODE = "['RESPONSECODE']"; //  响应码信息  
	String  CONTEXT_RESPONSEINFO = "['RESPONSEINFO']"; //  响应码信息  
	
	String CCMS990_HEADER_MAP = "['CCMS990_HEADER_MAP']"; // 发送ccms990 时组装的headermap
	String CCMS990_HEADER_BYTE = "['CCMS990_HEADER_BYTE']"; // 发送ccms990 时 组装header生成的byte[]
	
	// 每页记录条数 ，默认10  
	long PAGE_SIZE =  10;
}
