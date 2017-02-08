package cn.com.doit.before;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 注册带有@ConfigurationProperties注释的bean,可用于替代标准的注册方法，例如@bean,@Component
 * 
 * @Configuration is meta-annotated with @Component, therefore @Configuration
 *                classes are candidates for component scanning (typically using
 *                Spring XML's <context:component-scan/> element) and therefore
 *                may also take advantage of
 * @Autowired/@Inject at the field and method level (but not at the constructor
 *                    level).
 * @Configuration classes may not only be bootstrapped using component scanning,
 *                but may also themselves configure component scanning using the
 */
@Configuration
@EnableConfigurationProperties({ StudentProperties.class,
		CommandBeforeApp.class })
public class RegisterConfigurationPropertie {
}
