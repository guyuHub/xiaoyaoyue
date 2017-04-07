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
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import ch.qos.logback.core.db.BindDataSourceToJNDIAction;
import cn.com.doit.Validator.custom.UserInfoValidator;
import cn.com.doit.captcha.service.FreeReadCaptcha;
import cn.com.doit.mvc.service.LoginService;
import cn.com.doit.pojo.login.user_info;
import cn.com.doit.util.ApplicationFactoryUtil;
import cn.com.doit.util.ExtJSResponse;
import cn.com.doit.util.ModelToView;

@Controller
@RequestMapping("/sb")
@CrossOrigin
public class LonginController {
	private Log log = LogFactory.getLog(LonginController.class);
	private ApplicationFactoryUtil applicationFactoryUtil;
	private SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	@Resource(name = "loginService")
	private LoginService loginService;
	@Resource(name = "freeReadCaptcha")
	private FreeReadCaptcha freeReadCaptcha;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserInfoValidator());
	}

	@RequestMapping("/login")
	public String list(Map<String, Object> model) {
		model.put("noheader", true);
		return "Login";
	}
	
	@RequestMapping("/image")
	public void image(HttpServletResponse response) {
		String key = loginService.randomKey();
		Cookie img = new Cookie("imgkey", key);
		img.setMaxAge(120);// 60s
		response.addCookie(img);
		try {
			String resString = freeReadCaptcha.getImage("", 1,
					response.getOutputStream());
			loginService.addToCache(key, resString);
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@ResponseBody
	@RequestMapping("/userValidate")
	public Map<String, String> list(@CookieValue("imgkey") String cookie,
			@Valid user_info user, String code, BindingResult result) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (cookie == null || cookie.equals("")) {
			result.addError(new ObjectError("过期", "验证码过期，请重新请求验证码"));
		}
		loginService.validateCaptchaWihtCookie(cookie, result, code);
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			StringBuilder message = new StringBuilder();
			resultMap.put("status", "error");
			for (ObjectError objectError : errors) {
				message.append(objectError.getCode()
						+ objectError.getObjectName()
						+ objectError.getDefaultMessage());
			}
			resultMap.put("message", message.toString());

		} else {
			// 登陆成功后缓存标识
			user.setLogin(true);
			String st = loginService.addToCache(user);
			resultMap.put("key", st);
			resultMap.put("status", "sucess");
			resultMap.put("url", "main");
			resultMap.put("id", st);// 该id需要返回以供查询
			log.info("登陆成功，存入缓存标识为st");
		}
		return resultMap;
	}
}
