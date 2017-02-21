package cn.com.doit.bo.login.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import cn.com.doit.pojo.login.student_info;
import cn.com.doit.pojo.login.user_info;
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

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserInfoValidator());
	}

	@RequestMapping("/login")
	public String list(Map<String, Object> model) {
		System.out.println("看啥呢?");
		return "Login";
	}

	@RequestMapping("/userValidate")
	public String list(@Valid user_info user, BindingResult result) {
		System.out.println("咋的呢?");
		return "hello";
	}

}
