package cn.com.doit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.hornetq.HornetQAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.hornetq.HornetQConfigurationCustomizer;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;

import cn.com.doit.before.StudentProperties;
import cn.com.doit.cfg.MyBean;

/*Auto-configuration classes are regular Spring Configuration beans. They are located using the SpringFactoriesLoader mechanism (keyed against this class). Generally auto-configuration beans are @Conditional beans (most often using @ConditionalOnClass and @ConditionalOnMissingBean annotations).*/
//@ComponentScan
//@EnableAutoConfiguration(exclude=HornetQAutoConfiguration.class)
//@ServletComponentScan 
//@EnableEncryptableProperties
@SpringBootApplication
public class AppStart {
	public static void main(String[] args) {
		String[] ass={"--server.port=9999"};
ConfigurableApplicationContext context=new SpringApplicationBuilder()
		.bannerMode(Mode.OFF)
		.sources(AppStart.class)
		.addCommandLineProperties(true)
		.run(ass); 
cn.com.doit.cfg.MyBean bean = context.getBean(MyBean.class);
bean.printVariable();
//ReflectionUtils 反射工具类
//StudentProperties studentProperties=(StudentProperties) context.getBean("studentProperties");
//studentProperties.toTry();
	}

}
