package plugins.oracle;

public class test {
	public boolean bij(){

		 
Object o1="安徽省合肥市打饭区";
Object o2="安徽省巢湖市居巢区";
if(o1!=null&&o2!=null){
	String s1=(String)o1;
	String s2=(String)o2;
	int a=s1.indexOf("省");
	int b=s2.indexOf("省");
	if(s1.substring(0, a).equals(s2.substring(0, b))){
	     return true;
	}
}
return false;

	
	}
  public static void main(String[] args) {}
}
