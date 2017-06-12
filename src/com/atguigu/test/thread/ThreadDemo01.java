package com.atguigu.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 线程通信,生产者消费者模式
 * @author fangyi
 *
 */
class ShareData {
	private int number = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increament(int rows) {
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			number++;
			System.out.println("生产者: " + Thread.currentThread().getName() + "生产了一个产品,共有"+number+"件,这是第" + rows + "次生产");
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void decreament(int rows) {
		lock.lock();
		try {
			while (number == 0) {
				condition.await();
			}
			number--;
			System.out.println("消费者: " + Thread.currentThread().getName() + "消费了一个产品,共有"+number+"件,这是第" + rows + "次消费");
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}

public class ThreadDemo01 {

	public static void main(String[] args) {
		final ShareData shareData = new ShareData();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					shareData.increament(i);
				}
			}
		}, "AA").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					shareData.decreament(i);
				}
			}
		}, "BB").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					shareData.increament(i);
				}
			}
		}, "CC").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					shareData.decreament(i);
				}
			}
		}, "DD").start();
		

	}
}






