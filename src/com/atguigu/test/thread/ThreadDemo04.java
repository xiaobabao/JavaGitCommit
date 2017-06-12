package com.atguigu.test.thread;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author fangyi
 *
 */
class ShareData04 {
	private Object object;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void write(Object obj) {
		lock.writeLock().lock();
		try {
				object = obj;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void read() {
		lock.readLock().lock();
		try {
			System.out.println(object);
			object = 1000;
		} finally {
			lock.readLock().unlock();
		}
	}
}

public class ThreadDemo04 {

	public static void main(String[] args) {
		final ShareData04 shareData04 = new ShareData04();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					shareData04.write(new Random().nextInt(3));
				}
			}
		}, "BB").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					shareData04.read();
				}
			}
		}, "AA").start();
	}
}
