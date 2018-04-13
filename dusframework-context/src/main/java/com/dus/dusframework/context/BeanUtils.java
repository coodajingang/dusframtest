package com.dus.dusframework.context;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtils {


	/**
	 * 从runcontext中拷贝属性到目标do中；  
	 * 由于目前使用的mybatis生成器，do成员非驼峰结构， 而context中的是驼峰结构 ， 故特殊处理之  
	 * @param dest
	 */
	public static void copyProperty2DoCaseInsens(Object dest) {
		if (dest == null) {
			return ;
		}
		
		Map map = RunContextUtil.getHeadInfo(Map.class);
		copySimplePropertyCaseInsens(dest, map);
		
		try {
			org.apache.commons.beanutils.BeanUtils.setProperty(dest, "txncode", RunContextUtil.getTxnCode());
			org.apache.commons.beanutils.BeanUtils.setProperty(dest, "platflowno", RunContextUtil.getPlatFlowNo());
			org.apache.commons.beanutils.BeanUtils.setProperty(dest, "platdate", RunContextUtil.getPlatDate());
			org.apache.commons.beanutils.BeanUtils.setProperty(dest, "plattime", RunContextUtil.getPlatTime());
		} catch (Exception ex) {
			
		}
	}
	
	/**
	 * 从原bean中拷贝属性到目标do中；  
	 * 由于目前使用的mybatis生成器，do成员非驼峰结构， 而源bean中的是驼峰结构 ， 故特殊处理之  ;
	 * dest 是 do ， 其属性是全小写；  
	 * src 是原bean ， 其属性是驼峰结构  ； 
	 * @param dest
	 */
	public static void copyProperty2DoCaseInsens(Object dest, Object src) {
		if (dest == null || src == null) {
			return ;
		}
		
		Map map = new HashMap();
		
		genBeanPropertyMap(map , src.getClass() , src);
		
		for (Object key : map.keySet()) {
			System.out.println((String)key + " = " + map.get(key));
		}
		
		copySimplePropertyCaseInsens(dest, map);
		
	}
	
	public static void genBeanPropertyMap(Map map , Class<?> clazz, Object src) {
		if (clazz == null || src == null) {
			return ;
		}
		
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
		
		for (PropertyDescriptor pd : pds) {
			// 1. key 是name ， value 是其对应的值；  
			// 2. 若值为空 ，则退出 ； 取值为空 ，则退出 ；
			// 3. 只取其标准类型 ； int 、 short 、 long 、 double  、 float 、 String 、boolean 、Date 、 
			// 4. 对于 list  map 不处理  ；  对于其他类型， 递归调用；  需要检查是否有重复的属性  ， 有则不添加 ， 需要手工进行处理 ；  
			
			String key = pd.getName();
			Class type = pd.getPropertyType();
			
			Method readMethod = pd.getReadMethod();
			
			if (readMethod == null) {
				// 没有读属性则退出 ； 
				continue;
			}
			
			if (key.equals("class")) {
				continue;
			}
			if (type.isArray() ) {
				System.out.println(type.getName() + " 列表跳过！");
				continue;
			}
	        if (!Modifier.isPublic(readMethod.getModifiers())) {
	        	continue;
	        }
	        
	        Object value = null;
	        
	        System.out.println(key + "|" + type.getSimpleName() + "|" + readMethod.getName() );
	        
			try {
				value = readMethod.invoke(src, new Object[0]);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			if (value == null) {
				System.out.println(key + " 取值为空 跳过！");
				continue;
			}
			
			
			if (type.isPrimitive() || String.class.isAssignableFrom(type) || 
					BigDecimal.class.isAssignableFrom(type)) {
				// 是基本类型  
				if (void.class.isAssignableFrom(type)) {
					continue;
				}
				
				map.put(key, value);
				
			} else if (XMLGregorianCalendar.class.isAssignableFrom(type)){
				// Date  
				map.put(key, value);
			} else if ( type.isEnum()) {
				map.put(key, ((Enum)value).name());
			}
			else if (List.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)) {
				System.out.println("List or Map skiped！");
				continue;
			} else if (type.getSimpleName().equals("Object")) {
				System.out.println("Object skiped！");
				continue;
			} else {
				// 其他类 bean ； 取其值 ， 递归调用 该方法 生成map ； 
				System.out.println("递归：" + type);
				genBeanPropertyMap(map, type, value);
			}
			
		}
	}
	
	/**
	 * 从map中取值赋值到目标实例中；  注意map的key不区分大小写  ； 
	 * @param dest
	 * @param map
	 */
	public static void copySimplePropertyCaseInsens(Object dest, Map map) {
		if (dest == null || map == null || map.isEmpty()) {
			return;
		}
		
		for (Object key : map.keySet()) {
			String orgKey = (String) key;
			
			String lowerKey = orgKey.toLowerCase();
			
			Object value = map.get(key);
			try {
				org.apache.commons.beanutils.BeanUtils.setProperty(dest, lowerKey, value);
			} catch (Exception ex) {
				try {
					org.apache.commons.beanutils.BeanUtils.setProperty(dest, orgKey, value);
				} catch (Exception esx) {
					
				}
				
			}
			
		}
		
	}
	
	public static Object getValueFromMapCaseInsens(String key, Map map) {
		Object obj = map.get(key);
		
		if (obj == null) {
			key = key.substring(0, 1).toLowerCase() + key.substring(1);
			obj = map.get(key);
			
			if (obj == null) {
				key = key.toLowerCase();
				obj = map.get(key);
			}
		}
		return obj;
	}
}
