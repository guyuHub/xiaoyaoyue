package cn.com.doit.pojo.login.service;

import java.util.List;

import cn.com.doit.pojo.login.student_info;


public interface StudentBO {
	List<student_info> list(String userName);
	
	void add(student_info student);
	
	void delete(Integer student);
	
	void update(student_info student);
}
