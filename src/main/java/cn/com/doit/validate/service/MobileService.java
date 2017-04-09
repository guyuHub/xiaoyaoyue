/**
 * 
 */
package cn.com.doit.validate.service;

import cn.com.doit.validate.service.impl.ResponseResult;

/**
 * @author Administrator
 *
 */
public interface MobileService {
   public ResponseResult sendMsg(String phoneNum,String msg);
}
