package cn.com.doit.z.test.service;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.doit.z.test.pojo.TestStudent;

@Controller
@RequestMapping("/test")
public class TestService {
	@RequestMapping(method={RequestMethod.POST},value="/requestParamr")
 public String requestParamrTest(@RequestParam("name")String testnName,
		 @RequestParam("address")String testnAddress,
	      Integer testAge){
		String name=testnName;
		String address=testnAddress;
		Integer age=testAge;
	 return address+"的"+name+"is :"+age.toString()+"岁";
 }
	
	@RequestMapping(method={RequestMethod.POST},value="/requestDataBinder")
 public String requestDataBinderTest(TestStudent a){
		String name=a.getName();
		String address=a.getAdress();
		Integer age=a.getAge();
	 return address+"的"+name+"is :"+age.toString()+"岁";
 }
	@RequestMapping("/requestbody")
 public String requestBodyTest(@RequestBody TestStudent sb){
	System.out.println("------------------------"+sb.getAdress()+"的"+sb.getName()+"is :"+sb.getAge()+"岁");
	 return sb.getAdress()+"的"+sb.getName()+"is :"+sb.getAge()+"岁";
 }
	
	@RequestMapping(value="/date")
 public String requestDateTest(Date date){
		System.out.println("---------------------"+date.toLocaleString());
	 return "ceshi";
 }
}
