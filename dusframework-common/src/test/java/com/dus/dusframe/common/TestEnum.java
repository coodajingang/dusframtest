package com.dus.dusframe.common;

import org.junit.Test;

import com.dus.dusframework.common.IBaseEnum;
import com.dus.dusframework.common.util.ComUtils;

public class TestEnum {

	public enum myenum implements IBaseEnum<Integer> {
		
		first("fff", 1),
		second("ssss", 2);
		
		
		myenum(String id, int num) {
			this.id  = id;
			this.num = num;
		}
		
		private String id;
		private int num;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}

		public Integer getValue() {
			// TODO Auto-generated method stub
			return this.num;
		}
		
		
	}
	
	@Test
	public void test4enum() {
		System.out.println(myenum.values());
		
		myenum ddd = myenum.valueOf("first");
		
		System.out.println(ddd);
		
		for (myenum mm : myenum.values()) {
			System.out.println(mm);
		}
		
		myenum[] menums  =  myenum.class.getEnumConstants();
		
		for (myenum mm: menums) {
			System.out.println(mm);
		}
		
		System.out.println(ComUtils.constains(myenum.class, Integer.valueOf(2)));
		
		myenum mm = ComUtils.toEnum(myenum.class, 1);
		
		System.out.println(mm);
	}
	
	@Test
	public void test4www() {
		String str = "adadsfadf";
		
		System.out.println(ComUtils.subString(str, 5, 3));
		//System.out.println(str.substring(0,200));
	}
}
