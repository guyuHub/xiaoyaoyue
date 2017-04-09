package cn.com.doit.mvc.controller;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.db.BindDataSourceToJNDIAction;
import cn.com.doit.Validator.custom.UserInfoValidator;
import cn.com.doit.captcha.service.FreeReadCaptcha;
import cn.com.doit.mvc.service.MainService;
import cn.com.doit.mvc.service.LoginService;
import cn.com.doit.pojo.book.Free_book_info;
import cn.com.doit.pojo.login.user_info;
import cn.com.doit.pojo.main.MenuNode;
//import cn.com.bsfit.doit.z.test.schedule.TestScheduleDongTai;
import cn.com.doit.util.ApplicationFactoryUtil;
import cn.com.doit.util.ExtJSResponse;
import cn.com.doit.util.ModelToView;

@Controller
@RequestMapping("/sb")
@CrossOrigin
public class MainController {
	private Log log = LogFactory.getLog(MainController.class);
	private ApplicationFactoryUtil applicationFactoryUtil;
	private SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	@Resource(name = "loginService")
	private LoginService loginService;
	@Resource(name = "mainService")
	private MainService mainService;
  
/**
 * 
 * @param name 登陆用户的id值，根据此id值从as缓存中获取对象信息
 * @param model 载入用户对象信息(若果有的话),载入分级菜单信息
 * @return
 */
	@RequestMapping("/main")
	public String hello(String name, Map<String, Object> model) {
		user_info user=null;
		if(name==null||name.equals("")){
			
		}
		 user=loginService.getByCache(name);
		 List<MenuNode> menus=mainService.showMenus(user);
		 List<MenuNode> cloumMenus=mainService.showCloumMenus(user);
		model.put("user",user);
		model.put("menus",menus);
		model.put("cloumMenus",cloumMenus);  
		List<Free_book_info> cacheBooks=mainService.getCarouselBooks("carouselBooks");
	    model.put("carouselBooks", cacheBooks);
		 return "main";
	}
	/*
	 * 不能carouselBooks(Map<String, Object> model) {，用了后会去找页面，具体需要看源码，假期看下*/
	@ResponseBody
	@RequestMapping("/carouselBooks")
	public Map<String, Object> carouselBooks() {
		Map<String, Object> model=new HashMap<String, Object>();
		List<Free_book_info> cacheBooks=mainService.getCarouselBooks("carouselBooks");
	    model.put("carouselBooks", cacheBooks);
		return model;
	}
}
