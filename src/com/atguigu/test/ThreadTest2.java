package com.atguigu.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Resouce02 implements Callable<Integer>{
	private int a = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	@Override
	public Integer call() throws Exception {
		return null;
	}
}

public class ThreadTest2 {
	public static void main(String[] args) {
		Resouce02 resouce02 = new Resouce02();
		FutureTask<Integer> futureTask = new FutureTask<>(resouce02);
		new Thread(futureTask,"AA").start();
	}
}











