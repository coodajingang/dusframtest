package com.dus.dusframework.web.controller;

import java.util.Random;

/**
 * 返回json数据的 bean 
 * @author ThinkPad
 *
 */
public class DataTableBean {

	public DataTableBean() {
		// TODO Auto-generated constructor stub
	}
	
	public static DataTableBean genRandom() {
		DataTableBean b = new DataTableBean();
		Random rand = new Random(System.currentTimeMillis());
		
		b.setAge(rand.nextInt(100));
		b.setLevel(rand.nextLong());
		b.setScore(rand.nextDouble());
		b.setLastName("last Name of " + rand.nextInt());
		b.setName("name of " + rand.nextInt());
		return b;
	}
	
	
	
	@Override
	public String toString() {
		return "DataTableBean [name=" + name + ", lastName=" + lastName + ", age=" + age + ", level=" + level
				+ ", score=" + score + "]";
	}



	private String name;
	private String lastName;
	private int age;
	private long level;
	
	private double score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	

}
