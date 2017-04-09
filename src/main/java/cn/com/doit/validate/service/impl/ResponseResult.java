package cn.com.doit.validate.service.impl;

public class ResponseResult {
	private int code; // 状态码
	private String msg; // 此次发送的sendid
	private int obj;  //发送的验证码内容
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getObj() {
		return obj;
	}
	public void setObj(int obj) {
		this.obj = obj;
	}

	
}
