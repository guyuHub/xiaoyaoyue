package cn.com.doit.login.controller;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import ch.qos.logback.core.db.BindDataSourceToJNDIAction;
import cn.com.doit.Validator.custom.UserInfoValidator;
import cn.com.doit.login.service.LoginService;
import cn.com.doit.pojo.login.student_info;
import cn.com.doit.pojo.login.user_info;
//import cn.com.bsfit.doit.z.test.schedule.TestScheduleDongTai;
import cn.com.doit.util.ApplicationFactoryUtil;
import cn.com.doit.util.ExtJSResponse;
import cn.com.doit.util.ModelToView;

@Controller
@RequestMapping("/sb")
@CrossOrigin
public class LonginController {

	private ApplicationFactoryUtil applicationFactoryUtil;
	private SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
      @Resource(name="loginService")
	 private LoginService loginService;
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserInfoValidator());
	}

	@RequestMapping("/login")
	public String list(Map<String, Object> model) {
		System.out.println("看啥呢?");
		return "Login";
	}
	@RequestMapping("/hello")
	public String hello(Map<String, Object> model) {
		System.out.println("Come On..");
		return "hello";
	}
    @ResponseBody
	@RequestMapping("/userValidate")
	public Map<String, String> list(@Valid user_info user, BindingResult result) {
    	Map<String, String> resultMap=new HashMap<String, String>();
    	if(result.hasErrors()){
    	List<ObjectError> errors=	result.getAllErrors();
    	StringBuilder message=new StringBuilder();
    		resultMap.put("status","error");
    		for (ObjectError objectError : errors) {
				message.append(objectError.getCode());
			}
    		resultMap.put("message", message.toString());
    		
    	}else{
    		//登陆成功后缓存标识
    		String st=loginService.addToCache(user);	
    		resultMap.put("key", st);
    		resultMap.put("status", "sucess");
    		resultMap.put("url", "hello");
    	}
		return resultMap;
	}
//    @ResponseBody
//    @RequestMapping("/listJson")
//	public List<SimpleUser> listJson(String startTime,String endTime) throws ParseException {
//    	System.out.println("-----------"+startTime);
//    	System.out.println("***********"+endTime);
//    	List<SimpleUser> sb=new ArrayList<SimpleUser>();
//    	SimpleUser user1=new SimpleUser();
//    	user1.setBaoFooId("2018022701");
//    	user1.setBaooFooName("邦盛11");
//    	user1.setRegisterTime(sdf.parse("2017-02-27 10:11:11.000").getTime());
//    	user1.setUpdateTime(sdf.parse("2017-02-27 10:11:11.000").getTime());
//    	
//    	SimpleUser user2=new SimpleUser();
//    	user2.setBaoFooId("2018022702");
//    	user2.setBaooFooName("邦盛21");
//    	user2.setRegisterTime(sdf.parse("2017-02-27 10:12:11.000").getTime());
//    	user2.setUpdateTime(sdf.parse("2017-02-27 10:12:11.000").getTime());
//    	
//    	SimpleUser user3=new SimpleUser();
//    	user3.setBaoFooId("2018022703");
//    	user3.setBaooFooName("邦盛31");
//    	user3.setRegisterTime(sdf.parse("2017-02-27 10:13:11.000").getTime());
//    	user3.setUpdateTime(sdf.parse("2017-02-27 10:13:11.000").getTime());
//    	
//    	sb.add(user1);
//    	sb.add(user2);
//    	sb.add(user3);
//		return sb;
//	}
//    
//    @ResponseBody
//    @RequestMapping("/listTime")
//	public long listTime(String last) throws ParseException {
//    	if(last.equals("false")){
//    		return sdf.parse("2017-02-26 10:13:11.000").getTime();
//    	}else{
//    		return sdf.parse("2017-02-27 13:13:11.000").getTime();
//    	}
//    }
}
