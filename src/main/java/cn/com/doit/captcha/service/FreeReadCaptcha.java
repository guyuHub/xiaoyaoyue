package cn.com.doit.captcha.service;

import java.io.OutputStream;
import java.util.Map;

public interface FreeReadCaptcha {
	static int MUL=0;//乘法运算
	static int DIV=1;//除法运算
	static int ADD=2;//加法运算
	static int SUB=3;//减法运算
	/**
	 * 
	 * @param var 要生成的验证码的字符串
	 * @param leavel 干扰等级
	 * @return 生成后的图片验证码
	 */
     public String getImage(String var,int leavel, OutputStream out);
     
     /**
      * 
      * @param num1 参与计算的数字1
      * @param num2 参与计算的数字2
      * @param var  计算的算法运算符
      * @param leavel 干扰等级
      * @return Map包括运算结果和生成后的图片验证吗
      */
     public Map<String, Object> getImageWithMath(float num1,float num2,int var,int leavel);
}
