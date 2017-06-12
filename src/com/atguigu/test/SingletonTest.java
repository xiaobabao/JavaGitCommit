package com.atguigu.test;

public class SingletonTest {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					Singleton2 singleton2 = Singleton2.getSingleton2();
					System.out.println(Thread.currentThread().getName() + "\t" + singleton2);
				}
			}
		},"AA").start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					Singleton2 singleton2 = Singleton2.getSingleton2();
					System.out.println(Thread.currentThread().getName() + "\t" + singleton2);
				}
			}
		},"BB").start();

	}

}
