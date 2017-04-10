/**
 * 
 */
package cn.com.doit.validate.service;

import cn.com.doit.validate.service.impl.ResponseResult;

/**
 * @author Administrator
 *
 */
public interface EmailService {
   public ResponseResult sendMsg(String email,String msg);
}
