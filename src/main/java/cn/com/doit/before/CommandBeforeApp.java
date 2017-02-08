package cn.com.doit.before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/*if you need to run some specific code once the SpringApplication has started,
 *  you can implement the ApplicationRunner or CommandLineRunner interfaces. 
 *  Both interfaces work in the same way and offer a single run method
 *   which will be called just before SpringApplication.run(…​) completes.
 *   简而言之，就是在程序启动之前做些什么是
*/
@ConfigurationProperties(prefix = "my")
public class CommandBeforeApp implements CommandLineRunner {
	private int number;


	public void run(String... args) throws Exception {
		System.out.println(""+ number);
		for (String string : args) {
			System.out.println("in before :---" + string);
		}

	}



	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
