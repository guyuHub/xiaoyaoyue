package cn.com.doit.schedule.quartz;

import static org.quartz.JobBuilder.newJob;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import cn.com.doit.schedule.quartz.job.TestJobOne;
import cn.com.doit.schedule.quartz.trigger.TestTriggerOne;
import cn.com.doit.util.ApplicationFactoryUtil;

@Component
public class DoRun {
	@Autowired
	private Scheduler scheduler;
//取消注释后会自动执行
//	@PostConstruct
	public void dorun() {
		TestTriggerOne one = new TestTriggerOne();
		try {
			JobDetail aa = newJob(TestJobOne.class).withIdentity("jobone",
					"group1").build();
			scheduler.scheduleJob(aa, one.getTrigger());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
