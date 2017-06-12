package com.atguigu.test;

public class Singleton {

	private static Singleton singleton = new Singleton();

	private Singleton() {
	}

	public static Singleton getSingleton() {
		return singleton;
	}
}

class Singleton2 {

	private static Singleton2 singleton2 = null;

	private Singleton2() {

	}

	public static synchronized Singleton2 getSingleton2() {
		if (singleton2 == null) {
			singleton2 = new Singleton2();
		}
		return singleton2;
	}
}
