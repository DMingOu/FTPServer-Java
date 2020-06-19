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
 * @Description: �̴߳�������(�̳߳�)
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

	// �̳߳�
	public static class ThreadPool {

		private int corePoolSize;// �����߳���
		private int maximumPoolSize;// ����߳���
		private long keepAliveTime;// ��Ϣʱ��

		//�̳߳�executor
		private ThreadPoolExecutor executor;
		
		/**
		 * @Author DMingO 
		 * @Description ������
		 * @Date  2020/6/19 19:07
		 * @Param [corePoolSize, maximumPoolSize, keepAliveTime]
		 * @return �̳߳ض���
		 **/
		private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime = keepAliveTime;
		}

		public void execute(Runnable r) {
			if (executor == null){
				executor = new ThreadPoolExecutor(
						corePoolSize, //�����߳���
						maximumPoolSize,//����߳���
						keepAliveTime, //�߳�����ʱ��
						TimeUnit.SECONDS, //ʱ�䵥λ����
						new LinkedBlockingQueue<>(), //�̶߳���
						Executors.defaultThreadFactory(), //�̹߳���
						new AbortPolicy() //�߳��쳣�������
				);
			}
			//ִ������
			executor.execute(r);
		}

		// ȡ������
		public void cancel(Runnable r) {
			if (executor != null) {
				// ���̶߳������Ƴ�����
				executor.getQueue().remove(r);
			}
		}

	}
	
}
