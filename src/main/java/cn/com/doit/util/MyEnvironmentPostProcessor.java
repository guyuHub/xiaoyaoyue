package cn.com.doit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnumerableCompositePropertySource;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor{

	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		MutablePropertySources pp= environment.getPropertySources();
		PropertySource vv=pp.get("applicationConfigurationProperties");
ArrayList<PropertySource> zz=(ArrayList<PropertySource>) vv.getSource();
EnumerableCompositePropertySource bb=(EnumerableCompositePropertySource) zz.get(0);
HashMap<String, Object> sb =new HashMap<String, Object>();
sb.put("demo.jdbc.password", "---");
bb.add(new MapPropertySource("tihuan", sb));
	Object z=	vv.getProperty("demo.jdbc.password");
	System.out.println(z);
	}

}
