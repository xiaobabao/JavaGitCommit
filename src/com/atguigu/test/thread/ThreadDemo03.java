package com.atguigu.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信呼啦圈二
 * @author fangyi
 *
 */
class ShareData03 {
	private int status = 1;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	public void loopA() throws InterruptedException {
		lock.lock();
		try {
			while (status != 1) {
				condition1.await();
			}
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			status = 2;
			condition2.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void loopB() throws InterruptedException {
		lock.lock();
		try {
			while (status != 2) {
				condition2.await();
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			status = 3;
			condition3.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void loopC() throws InterruptedException {
		lock.lock();
		try {
			while (status != 3) {
				condition3.await();
			}
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			status = 1;
			condition1.signal();
		} finally {
			lock.unlock();
		}
	}
}

public class ThreadDemo03 {
	public static void main(String[] args) {
		final ShareData03 shareData03 = new ShareData03();
		System.out.println(shareData03);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 5; i++) {
						shareData03.loopA();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "AA").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 5; i++) {
						shareData03.loopB();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 5; i++) {
						shareData03.loopC();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();
	}
}
