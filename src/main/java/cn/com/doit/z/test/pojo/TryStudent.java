package cn.com.doit.z.test.pojo;

import java.util.Enumeration;

import javax.annotation.PostConstruct;
import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.doit.util.ApplicationFactoryUtil;

@Component
public class TryStudent {
	@Value("${student.name}")
	private  transient String name;
	@Value("${student.age}")
	private  transient Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@PostConstruct
	public void toSay(){
		System.out.println("-------------==="+this.getName()+ApplicationFactoryUtil.getServletContext().getAttributeNames());
		  Enumeration<String> sb=ApplicationFactoryUtil.getServletContext().getAttributeNames();
		  if(sb.hasMoreElements()){
			 System.out.println(sb.nextElement());
		 }
	}
}
