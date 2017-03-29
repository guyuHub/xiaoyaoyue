package cn.com.doit.main.service;

import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;

import cn.com.doit.pojo.book.Free_book_info;
import cn.com.doit.pojo.login.user_info;
import cn.com.doit.pojo.main.MenuNode;


public interface MainService {
          public List<MenuNode>  showMenus(user_info user);
          public List<MenuNode>  showCloumMenus(user_info user);
		/**
		 * @return
		 */
		public List<Free_book_info> getCarouselBooks(String key);
}
