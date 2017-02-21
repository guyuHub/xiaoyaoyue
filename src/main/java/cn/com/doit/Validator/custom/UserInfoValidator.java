package cn.com.doit.Validator.custom;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cn.com.doit.pojo.login.user_info;

public class UserInfoValidator implements Validator {

	public boolean supports(Class<?> clazz) {   
		return user_info.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		if(target instanceof user_info){
			user_info user=(user_info) target;
			if(user.getName().equals("大神")){
				errors.rejectValue("name", "Not The People");
			}else if(user.getPassword().equals("hundan")){
				errors.rejectValue("password", "Not The pass");
			}
		}else{
			errors.rejectValue("class", "Not The user_info");
		}
		
		

	}

}
