package cn.com.doit.schedule.quartz.trigger;

import org.quartz.Trigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import static org.quartz.TriggerBuilder.newTrigger;

public class TestTriggerOne {
  public Trigger getTrigger(){
	Trigger  trigger = newTrigger()
			    .withIdentity("TestTriggerOne", "group1")
			    .withSchedule(cronSchedule("0/10 * * * * ?"))
			    .build();
	  return trigger;
  }
}
