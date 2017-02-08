package toTry;;

public class ThreadC {
	volatile boolean cando=true;
   public static void main(String[] args) {
	   String a=new String("a");
	   
	   String c=a+"b";
	   String s = null;  
	// s.intern();
	 String b="null";
	 System.out.println(String.valueOf(s));
}
	
}
