package cn.com.doit.before;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "student")
public class StudentProperties {
	private String name;
	private String sex;
	private String age;

	public void toTry(){
		System.out.println("This is in StudentProperties to show :"+this.getName());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
