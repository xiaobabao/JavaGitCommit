package com.atguigu.test;

public class Children extends Father implements MyInterface{
	
	private static int age = 20;
	@Override
	public int getAge() {
		return age;
	}
	@Override
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public int go()  {
		return age;
	}
}
