package com.dus.dusframework.common.util;

import com.dus.dusframework.common.IBaseEnum;
import com.dus.dusframework.common.InvalidateEnumValueException;

public class ComUtils {

	public static String getErrorFromStackTrace(StackTraceElement[] sss) {
		StringBuilder sb = new StringBuilder(200);
		
		for (StackTraceElement se : sss) {
			sb.append(se.toString());
		}
		
		return ComUtils.subString(sb.toString(), 0, 200);
	}
	
	public static boolean startWith(String src, String pref) {
		return isNotEmpty(src) && src.startsWith(pref);
	}
	
	public static boolean endWith(String src, String suffix) {
		return isNotEmpty(src) && src.endsWith(suffix);
	}
	
	public static String subString(String str , int start , int end) {
		if (isEmpty(str)) {
			return str;
		}
		
		if (start > end) {
			return null;
		}
		
		int len = ComUtils.length(str);
		
		if (start >= len) {
			return null;
		}
		
		if (end > len) {
			end = len;
		}
		return str.substring(start, end);
	}
	
	public static String repeat(String str, int time) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < time ; ++i ) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	public static String leftPad(String src, String padStr) {
		return padStr + src;
	}
	
	public static String rightPad(String src, String padStr) {
		return src + padStr;
	}
	
	public static String[] split(String str , String sep) {
		if (isEmpty(str)) {
			return new String[0];
		}
		return str.split(sep);
	}
	
	public static boolean contains(String src, String... container) {
		if (isEmpty(src)) {
			return false;
		}
		
		if (container == null || container.length == 0) {
			return false;
		}
		
		for (String str : container) {
			if (equals(str, src)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * 检查obj值是否在枚举类 ；  用于常量检查; 
	 * 只适用于 基础封包类型的检查 
	 * @param clazz
	 * @param obj
	 * @return
	 */
	public static <O,T extends IBaseEnum<O>> boolean constains(Class<T> clazz, O obj) {
		IBaseEnum<O>[] ts = clazz.getEnumConstants();
		
		if (obj == null) {
			return false;
		}
		
		for (IBaseEnum<O> tt : ts) {
			if (obj.equals(tt.getValue())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 将值obj转换为对应的枚举类型  ； 不能转换则 抛出异常 
	 * @param clazz
	 * @param obj
	 * @return
	 */
	public static <O, T extends IBaseEnum<O>> T toEnum(Class<T> clazz ,O obj) {
		T[] ts = clazz.getEnumConstants();
		
		
		if (obj == null) {
			throw new InvalidateEnumValueException("E", "转换枚举类型输入值为空异常！" + obj);
		}
		for (T tt : ts) {
			if (obj.equals(tt.getValue())) {
				return tt;
			}
		}
		
		throw new InvalidateEnumValueException("E", "输入值没有对应的枚举！" + obj);
	}
	
	public static String join(String[] strs , String sep) {
		int length  = strs.length;
		if (0 == length) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder(strs[0]);
		
		for (int i = 1 ; i < length ; ++i) {
			sb.append(sep).append(strs[i]);
		}
		return sb.toString();
	}
	
	public static int length(String str) {
		if (isEmpty(str)) {
			return 0 ;
		}
		return str.length();
	}
	/**
	 * 判断对象是否为空 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否为空  
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		return !isNotEmpty(str);
	}
	
	public static boolean isNotEmpty(String str) {
		return str!=null && str.length() > 0;
	}
	
	public static boolean equals(String str1, String str2) {
		if (isEmpty(str1) && isEmpty(str2)) {
			return true;
		}
		
		return str1 != null && str2 != null && str1.equals(str2);
	}
	
	public static String trims(String str) {
		if (str == null) {
			return null;
		}
		return str.trim();
	}
	
	/**
	 * 比较两个字节数组 ， 自开始位置是否相同 
	 * @param src
	 * @param startpos
	 * @param dest
	 * @param despos
	 * @param len
	 * @return
	 */
	public static boolean memcomp(byte[] src, int startpos, byte[] dest, int despos, int len) {
		if (src == null && dest == null) {
			return true;
		}
		if (src == null || dest == null) {
			return false;
		}
		if (startpos + len > src.length) {
			return false;
		}
		if (despos + len > dest.length) {
			return false;
		}
		
		for (int i=0 ; i < len; ++i) {	
			if (src[startpos + i] != dest[despos + i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检查字节dest在src的开始位置 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static int memcontains(byte[] src, byte[] dest) {
		if (src == null || dest == null) {
			return -1;
		}
		if (dest.length > src.length) {
			return -1;
		}

		for (int i = 0 ; i < src.length; ++i) {
			if (memcomp(src, i, dest, 0, dest.length)) {
				return i;
			}
		}
		return -1;
	}
	
}
