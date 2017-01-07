package initializationAndShutdown;

import static org.quartz.CronScheduleBuilder.cronSchedule; 
import static org.quartz.JobBuilder.newJob; 
import static org.quartz.TriggerBuilder.newTrigger; 

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class Task {
	public void go(){
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			Scheduler scheduler = sf.getScheduler();
			JobDetail job = newJob(WebJob.class).withIdentity("job", "group").build();
			CronTrigger trigger = newTrigger().withIdentity("trigger", "group").withSchedule(cronSchedule("0 0 2 * * ?")).build();
			scheduler.scheduleJob(job, trigger);
			
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}		
	}
}
