package cn.edu.gdut.ftp.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class ThreadUtil {
	
	private static ThreadPool mThreadPool;
	
	public static ThreadPool getThreadPool() {
		if (mThreadPool == null) {
			synchronized (ThreadUtil.class) {
				if (mThreadPool == null) {
					int cpuCount = Runtime.getRuntime().availableProcessors();// ��ȡcpu����
					System.out.println("cpu����:" + cpuCount);
					int threadCount = 10;
					mThreadPool = new ThreadPool(threadCount, threadCount, 0l);
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

		private ThreadPoolExecutor executor;

		private ThreadPool(int corePoolSize, int maximumPoolSize,
				long keepAliveTime) {
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime = keepAliveTime;
		}

		public void execute(Runnable r) {
		if (executor == null) {
			executor = new ThreadPoolExecutor(corePoolSize,
					maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>(),
					Executors.defaultThreadFactory(), new AbortPolicy());
			// ��1:�����߳���;��2:����߳���;��3:�߳�����ʱ��;��4:ʱ�䵥λ;��5:�̶߳���;��6:�����̵߳Ĺ���;��7:�߳��쳣�������
		}
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
