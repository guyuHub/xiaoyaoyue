package toTry;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadToSay {

	private AtomicInteger sizeA=new AtomicInteger(0);
	private AtomicInteger sizeB=new AtomicInteger(0);
	private volatile boolean runA=true;
	 private Thread a=new Thread(new Runnable() {
		public void run() {
		while(true){
			if(runA){
				System.out.println("====in A=="+sizeA.addAndGet(1));
		}
			
			if(sizeA.get()==50){
				runA=false;
			     sizeB.set(0);		
		}
	}		
		}
	});
	 
	 private Thread b=new Thread(new Runnable() {		
		public void run() {
	while(true){
		if(!runA){
		int v=sizeB.addAndGet(1);
			System.out.println("====in B=="+v);
			if(sizeB.get()==100){
				runA=true;
					sizeA.set(0);			
		}						
		}
	}			
		}
	});
	 
	 public static void main(String[] args) {
		 ThreadToSay vv=new ThreadToSay();
		 vv.a.start();
		 vv.b.start();
	}
}
