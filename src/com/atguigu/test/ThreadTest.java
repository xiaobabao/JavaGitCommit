package com.atguigu.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
	private int number = 40;
	private AtomicInteger tick = new AtomicInteger(50);
	private ReentrantLock lock = new ReentrantLock();

	public void sale() {
		lock.lock();
		try {
			if (number > 0) {
				System.out.println("售票员: " + Thread.currentThread().getName() + "已卖出第" + number-- + "张票 ," + "还有" + number + "张");
			}
			if (tick.intValue() > 0) {
				System.out.println("<>售票员: " + Thread.currentThread().getName() + "已卖出第" + tick.getAndDecrement() + "张票 ," + "还有" + tick.intValue() + "张");
			}
		} finally {
			lock.unlock();
		}
	}
}

public class ThreadTest {
	public static void main(String[] args) {
		final Ticket ticket = new Ticket();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					ticket.sale();
				}
			}
		}, "AA").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					ticket.sale();
				}
			}
		}, "BB").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					ticket.sale();
				}
			}
		}, "CC").start();
		
	}
}
