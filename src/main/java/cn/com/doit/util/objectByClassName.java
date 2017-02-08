package cn.com.doit.util;

import cn.com.doit.pojo.login.student_info;

public class objectByClassName {
	private static  student_info student=new student_info();

	public objectByClassName() {
		
	}
	
	public static student_info getStudent() {
		return student;
	}

	public static void setStudent(student_info student) {
		objectByClassName.student = student;
	}
	
}
