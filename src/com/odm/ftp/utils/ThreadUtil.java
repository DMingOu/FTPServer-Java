package com.odm.ftp.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * @ClassName: ThreadUtil
 * @Auther: DMingO
 * @Date: 2020/6/11 15:45
 * @Description: 线程处理工具类(线程池)
 */
public class ThreadUtil {
	
	private static ThreadPool mThreadPool;
	
	public static ThreadPool getThreadPool() {
		if (mThreadPool == null) {
			synchronized (ThreadUtil.class) {
				if (mThreadPool == null) {
					int threadCount = 10;
					mThreadPool = new ThreadPool(threadCount, threadCount, 0L);
				}
			}
		}

		return mThreadPool;
	}

	// 线程池
	public static class ThreadPool {

		private int corePoolSize;// 核心线程数
		private int maximumPoolSize;// 最大线程数
		private long keepAliveTime;// 休息时间

		//线程池executor
		private ThreadPoolExecutor executor;
		
		/**
		 * @Author DMingO 
		 * @Description 构造器
		 * @Date  2020/6/19 19:07
		 * @Param [corePoolSize, maximumPoolSize, keepAliveTime]
		 * @return 线程池对象
		 **/
		private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime = keepAliveTime;
		}

		public void execute(Runnable r) {
			if (executor == null){
				executor = new ThreadPoolExecutor(
						corePoolSize, //核心线程数
						maximumPoolSize,//最大线程数
						keepAliveTime, //线程休眠时间
						TimeUnit.SECONDS, //时间单位：秒
						new LinkedBlockingQueue<>(), //线程队列
						Executors.defaultThreadFactory(), //线程工厂
						new AbortPolicy() //线程异常处理策略
				);
			}
			//执行任务
			executor.execute(r);
		}

		// 取消任务
		public void cancel(Runnable r) {
			if (executor != null) {
				// 从线程队列中移除对象
				executor.getQueue().remove(r);
			}
		}

	}
	
}
