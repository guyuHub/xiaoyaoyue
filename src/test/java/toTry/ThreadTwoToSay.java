package toTry;

public class ThreadTwoToSay {


	private int sizeA=0;
	private int sizeB=0;
	private volatile boolean runA=true;
	 private Thread a=new Thread(new Runnable() {
		public void run() {
		while(true){
			if(runA){
				System.out.println("====in A=="+sizeA++);
				if(sizeA==50){
					 sizeB=0;	
					runA=false;}
				     }		
		      else{
			          try {
			        		Thread.currentThread().sleep(20L);
			              } catch (InterruptedException e) {
				            e.printStackTrace();
			                                               }
		           }
		
	               }
		                    }                      }
		);
	 
	 private Thread b=new Thread(new Runnable() {		
		public void run() {
	while(true){
		if(!runA){
			System.out.println("====in B=="+sizeB++);
			if(sizeB==100){
				sizeA=0;	
				runA=true;}						
		}else{
			try {
				Thread.currentThread().sleep(20L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}			
		}
	});
	 
	 public static void main(String[] args) {
		 ThreadTwoToSay vv=new ThreadTwoToSay();
		 vv.a.start();
		 vv.b.start();
	}

}
