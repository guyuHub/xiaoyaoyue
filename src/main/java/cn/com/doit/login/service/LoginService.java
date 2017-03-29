package cn.com.doit.login.service;

import org.springframework.validation.BindingResult;

import cn.com.doit.pojo.login.user_info;
import cn.com.doit.util.cacheService;


public interface LoginService{
	
    public boolean queryBySql(String name,String password);

    public String addToCache(user_info user);
	
    public user_info getByCache(String key);
	
    public String randomKey();
    public void addToCache(String key,String value);

	/**
	 * @param cookie
	 * @param result
	 */
	public void validateCaptchaWihtCookie(String cookie, BindingResult result,String code);
}
