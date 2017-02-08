package cn.com.doit.schedule.springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class ScheduleConfig {
//@Bean
public MyScheduledAnnotationBeanPostProcessor scheduledAnnotationProcessor() {
	return new MyScheduledAnnotationBeanPostProcessor();
}
}
