package com.dus.dusframe.testbeanutils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import com.dus.dusframework.context.BeanUtils;

public class TestBeanUtils {

	@Test
	public void test4beantutils() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		CcmsT303 ccms = new CcmsT303();
		
		map.put("msgId", "msgId");
		map.put("vno", 38);
		map.put("dacVerf", "dacverf");
		map.put("resInfo", "resinfo");
		map.put("channelCgy", "channelcgy");
		map.put("tms", "20171123235959");
		map.put("platflowno", null);
		BeanUtils.copySimplePropertyCaseInsens(ccms, map);
		
		System.out.println(ccms.toString());
	}
	
	@Test
	public void test4beanutil2s() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		CcmsT303 ccms = new CcmsT303();
		ccms.setVno(38);
		ccms.setMesgdirection("D");
		Map map = org.apache.commons.beanutils.BeanUtils.describe(ccms);
		
		for (Object key : map.keySet()) {
			
			System.out.println((String)key + " " + map.get(key));
		}
		
	}
	
	
	@Test
	public void test4PropertyUtils() {
		PropertyDescriptor[] pdes = PropertyUtils.getPropertyDescriptors(User.class);
		
		for (PropertyDescriptor pd : pdes) {
			System.out.println(pd.getName() + " " + pd.getDisplayName() + " " + pd.getShortDescription());
			System.out.println(pd.getPropertyType() + " " + pd.getReadMethod() + " " + pd.getWriteMethod());
			
		}
		
		System.out.println("=======================================");
		
		Class type = void.class;
		
		System.out.println(type.isAssignableFrom(void.class));
		System.out.println(type.isAssignableFrom(Object.class));
		
		Map mm = new HashMap();
		System.out.println(mm.getClass().isAssignableFrom(Map.class));
		System.out.println(mm.getClass().isAssignableFrom(Object.class));
		System.out.println(Map.class.isAssignableFrom(mm.getClass()));
	}
	
	@Test
	public void test4PropertyUtils2() {
		User user = new User();
		user.setAge(18);
		user.setMeal(ManMail.MAN);
		user.setName("haha");
		user.setScore(300);
		user.setStatus(false);
		user.setUser("pass");
		user.setSutd(new Students());
		Map map = new HashMap();
		
		BeanUtils.genBeanPropertyMap(map, User.class, user);
		
		for (Object key : map.keySet()) {
			System.out.println((String)key + " = " + map.get(key));
		}
	}
	
}
