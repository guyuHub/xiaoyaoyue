package plugins.oracle;

public class test {
	public boolean bij(){

		 
Object o1="����ʡ�Ϸ��д���";
Object o2="����ʡ�����оӳ���";
if(o1!=null&&o2!=null){
	String s1=(String)o1;
	String s2=(String)o2;
	int a=s1.indexOf("ʡ");
	int b=s2.indexOf("ʡ");
	if(s1.substring(0, a).equals(s2.substring(0, b))){
	     return true;
	}
}
return false;

	
	}
  public static void main(String[] args) {}
}
