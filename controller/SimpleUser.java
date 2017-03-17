package cn.com.doit.login.controller;

public class SimpleUser{

    private  String baoFooId;   //宝付商户ID
    
    private  String baooFooName; //宝付商户名称
    
    private  long registerTime; //宝付商户注册时间
    
    private long updateTime;  //宝付商户更新时间
    
	public String getBaoFooId() {
		return baoFooId;
	}

	public void setBaoFooId(String baoFooId) {
		this.baoFooId = baoFooId;
	}

	public String getBaooFooName() {
		return baooFooName;
	}

	public void setBaooFooName(String baooFooName) {
		this.baooFooName = baooFooName;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
    
    

}
