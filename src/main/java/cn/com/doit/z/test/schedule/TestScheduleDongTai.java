package cn.com.doit.z.test.schedule;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.stereotype.Component;

import cn.com.doit.schedule.springmvc.MyScheduledAnnotationBeanPostProcessor;
import cn.com.doit.schedule.springmvc.MyScheduledTaskRegistrar;
import cn.com.doit.util.ApplicationFactoryUtil;
//测试spring mvc自带的schedule动态配置
@Component
public class TestScheduleDongTai {
	private ApplicationFactoryUtil ab=new ApplicationFactoryUtil();
//	@Scheduled(fixedDelay=5000)
  public void myTest(){
	  System.out.println("Schedule测试开始>>>>>>>>>------"+new Date().toLocaleString());
  }

	 public void myUpdateTest(){
		MyScheduledAnnotationBeanPostProcessor gg=ab.getCtx().getBean(MyScheduledAnnotationBeanPostProcessor.class);
		MyScheduledTaskRegistrar bb=gg.getRegistrar();
		Runnable old = null;
		List<IntervalTask> jihe= bb.getFixedDelayTasks();
		 Iterator<IntervalTask> stuIter = jihe.iterator();
		 while (stuIter.hasNext()) {  
			 IntervalTask student = stuIter.next();  
	            if ( student.getInterval()== 5000)  {
	            	old=student.getRunnable();
	               // stuIter.remove();  
	            	}
	        }  
		 Set<ScheduledFuture<?>> vv= bb.getTocancel();
		 for (ScheduledFuture<?> scheduledFuture : vv) {
			 scheduledFuture.cancel(true);
		}
		 IntervalTask pp=new IntervalTask(old, 10000L);
		 bb.getTaskScheduler().scheduleWithFixedDelay(pp.getRunnable(), pp.getInterval());
		  System.out.println("改变开始>>>>>>>>>------");
	}
}
