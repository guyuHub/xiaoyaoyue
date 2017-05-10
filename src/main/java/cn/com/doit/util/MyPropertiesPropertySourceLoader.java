package cn.com.doit.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class MyPropertiesPropertySourceLoader implements PropertySourceLoader {

	public String[] getFileExtensions() {
		return new String[] { "properties", "xml" };
	}

	
	public PropertySource<?> load(String name, Resource resource, String profile)
			throws IOException {
		if (profile == null) {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
//			if(properties.containsKey("demo.jdbc.password")){
//				properties.setProperty("demo.jdbc.password", "ppp");
//			}
			if (!properties.isEmpty()) {
				return new PropertiesPropertySource(name, properties);
			}
		}
		return null;
	}

}