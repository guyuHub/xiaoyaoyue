package cn.com.doit.schedule.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import cn.com.doit.schedule.quartz.job.TestJobOne;
import cn.com.doit.schedule.quartz.trigger.TestTriggerOne;
import static org.quartz.JobBuilder.newJob;
@Configuration
public class CfgOfQuartz {
	
	@Bean(name="scheduler")
	public SchedulerFactoryBean getSchedulerFactoryBean(){
		return new SchedulerFactoryBean();
	}
}
