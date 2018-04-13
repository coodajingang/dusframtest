package com.dus.dusframework.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.dus.dusframework.common.ICommonConstants;

public class DateUtils {

	public static ThreadLocal<Map<String, DateFormat>> dateFormatCache = new ThreadLocal<Map<String, DateFormat>>();
	
	public static Date getSysDate() {
		return new Date();
	}
	
	public static Date getBizDate() {
		// 由两部分  ， 取营业日期  ，拼接 当前时间  ， 貌似不太好 
		return new Date();
	}
	
	
	public static String getDate8Str(Date date) {
		return getDatePattern(ICommonConstants.DATE_FORMAT8, date);
	}
	
	public static String getDate14Str(Date date) {
		return getDatePattern(ICommonConstants.DATE_FORMAT14, date);
	}
	
	public static String getDate17Str(Date date) {
		return getDatePattern(ICommonConstants.DATE_FORMAT17, date);
	}
	
	public static String getDate8Str() {
		return getDatePattern(ICommonConstants.DATE_FORMAT8, null);
	}
	
	public static String getDate14Str() {
		return getDatePattern(ICommonConstants.DATE_FORMAT14, null);
	}
	
	public static String getDate17Str() {
		return getDatePattern(ICommonConstants.DATE_FORMAT17, null);
	}
	
	public static String getTime6Str(Date date) {
		return getDatePattern(ICommonConstants.TIME_FORMAT6, date);
	}
	
	public static String getTime6Str() {
		return getDatePattern(ICommonConstants.TIME_FORMAT6, null);
	}
	
	public static String getIsoDate8(Date date) {
		return getDatePattern(ICommonConstants.DATE_ISO_FORMAT8, date);
	}
	
	public static String getIsoDate8() {
		return getDatePattern(ICommonConstants.DATE_ISO_FORMAT8, null);
	}
	
	public static String getIsoDate14(Date date) {
		return getDatePattern(ICommonConstants.DATE_ISO_FORMAT8, date) + "T" + getDatePattern(ICommonConstants.DATE_ISO_FORMAT14_LAST, date);
	}
	
	public static String getIsoDate14() {
		return getDatePattern(ICommonConstants.DATE_ISO_FORMAT8, null) + "T" + getDatePattern(ICommonConstants.DATE_ISO_FORMAT14_LAST, null);
	}
	
	public static XMLGregorianCalendar getXmlGenCanlen() {
		DatatypeFactory dataTypeFactory;
		try {
			dataTypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(System.currentTimeMillis());
		return dataTypeFactory.newXMLGregorianCalendar(gc);
	}
	
	public static String getDatePattern(String pattern , Date date) {
		Map<String, DateFormat> dfCache = dateFormatCache.get();
		
		if (dfCache == null) {
			dfCache = new HashMap<String, DateFormat>();
			dateFormatCache.set(dfCache);
		}
		
		DateFormat df = dfCache.get(pattern);
		
		if (df == null) {
			df = new SimpleDateFormat(pattern);
			dfCache.put(pattern, df);
		}
		
		if (date == null) {
			date = new Date();
		}
		
		return df.format(date);
	}
}
