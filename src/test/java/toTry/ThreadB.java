package toTry;;

public class ThreadB extends Thread {
	int total;

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 101; i++) {
				total += i;
			}
			System.out.println("我在执行中哦...");
			// 通知所有在此对象上等待的线程
			notifyAll();
		}
	}
	
}
