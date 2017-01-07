package emailOperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailThreadPool {
	private ExecutorService threadPool = null;
	private static EmailThreadPool emailThreadPool = null;
	
	private EmailThreadPool(){
		this.threadPool = Executors.newFixedThreadPool(10);
	}
	
	public static EmailThreadPool getEmailThreadPool(){
		if(emailThreadPool == null){
			synchronized(EmailThreadPool.class){
				if(emailThreadPool == null){
					emailThreadPool = new EmailThreadPool();
				}
			}
		}
		return emailThreadPool;
	}
	
	public void sendEmail(String userName,String email,String movieName){
		EmailRunnable emailRunnable = new EmailRunnable(userName,email,movieName);
		threadPool.execute(emailRunnable);
	}
}
