package com.atguigu.test;

public class JavaTest {
	
	public static void main(String[] args) {
		Father f = new Children();
		System.out.println(f.getAge());
		f.setAge(15);
		System.out.println(f.go());
		System.out.println(f.gogo());
		
	}
}
