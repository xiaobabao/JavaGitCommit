package com.atguigu.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信之呼啦圈
 * @author fangyi
 *
 */
class ShareData2 {
	private int status = 1;
	private int number = 1;
	private char zimu = 'A';
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
			for (int i = 0; i < 2; i++) {
				System.out.print(number++);
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
			System.out.println(zimu++);
			status = 1;
			condition1.signal();
		} finally {
			lock.unlock();
		}
	}
}

public class ThreadDemo02 {
	public static void main(String[] args) {
		
		final ShareData2 shareData2 = new ShareData2();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 26; i++) {
						shareData2.loopA();
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
					for (int i = 0; i < 26; i++) {
						shareData2.loopB();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();
	}
}
