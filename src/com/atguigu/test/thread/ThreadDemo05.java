package com.atguigu.test.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 
 * @author fangyi
 *
 */
public class ThreadDemo05 {
	public static void main(String[] args) {
		
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> result = null;
		try {
			for (int i = 0; i < 10; i++) {
				result = threadPool.schedule(new Callable<Integer>() {
					
					@Override
					public Integer call() throws Exception {
						System.out.print(Thread.currentThread().getName());
						return new Random().nextInt(50);
					}
				}, 2, TimeUnit.SECONDS);
				System.out.println("*******: "+result.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
		
	}

	public static void threadPoolTest() {
		//ExecutorService threadPool = Executors.newFixedThreadPool(5);
		//ExecutorService threadPool = Executors.newSingleThreadExecutor();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Future<Integer> submit = null;
		try {
			for (int i = 0; i < 20; i++) {
				submit = threadPool.submit(new Callable<Integer>() {

					@Override
					public Integer call() throws Exception {
						System.out.print(Thread.currentThread().getName() + "\t");
						return new Random().nextInt(10);
					}
				});
				System.out.println(submit.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}

}
