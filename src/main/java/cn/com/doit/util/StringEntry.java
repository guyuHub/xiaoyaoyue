package cn.com.doit.util;

import java.security.KeyStore;
import java.util.Date;
//该类用于应用程序授权，存于证书中
public class StringEntry implements KeyStore.Entry{
     private String content;   //备注相关内容
     private Date   start;     //授权起始时间
     private Date    end;      //授权结束时间
     private long    day;      //授权天数 
     private String  mac;      //授权机器MAC
     
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public long getDay() {
		return day;
	}
	public void setDay(long day) {
		this.day = day;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
     
     
}
