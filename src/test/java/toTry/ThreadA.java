package toTry;


public class ThreadA extends Thread {
	ThreadB threadB;

	public ThreadA(ThreadB threadB) {
		this.threadB = threadB;
	}

	public void run() {
		synchronized (threadB) {
			try {
				System.out.println(Thread.currentThread() + "等待计算结果。。。");
				this.threadB.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + "计算结果为：" + threadB.total);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadB b = new ThreadB();
		// 启动三个线程，分别获取计算结果
		new ThreadA(b).start();
		new ThreadA(b).start();
		new ThreadA(b).start();
		Thread.currentThread().sleep(100L);
		// 启动计算线程
		b.start();
	}
}
