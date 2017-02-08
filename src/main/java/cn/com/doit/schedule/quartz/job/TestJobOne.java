package cn.com.doit.schedule.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJobOne implements Job{

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("------this is in TestJobOne------"+new Date().toLocaleString());
	}

}
