package cn.com.doit.bo.login.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import cn.com.doit.pojo.login.student_info;
//import cn.com.bsfit.doit.z.test.schedule.TestScheduleDongTai;
import cn.com.doit.pojo.login.service.StudentBO;
import cn.com.doit.util.ApplicationFactoryUtil;
import cn.com.doit.util.ExtJSResponse;
import cn.com.doit.util.ModelToView;



@Controller
@RequestMapping("/sb")
@CrossOrigin
public class LonginService {

 private ApplicationFactoryUtil applicationFactoryUtil;

	@RequestMapping("/login")
	public String list(Map<String, Object> model){
  
//	List<String> l= new ArrayList();  
//		    l.add("哈喽，hadoop");  
//		    l.add("哈喽，hbase");  
//		    l.add("哈喽，hive");  
//		    l.add("哈喽，pig");  
//		    l.add("哈喽，zookeeper");  
//		    l.add("哈喽，三劫散仙");  
//		    //将数据存放map里面，可以直接在velocity页面，使用key访问  
//		    model.put("data",l);  
		  System.out.println("看啥呢?");
		    return "Login";  

	}
	

}
