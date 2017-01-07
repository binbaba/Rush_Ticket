package initializationAndShutdown;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import model.Rush_Information;
import model.Ticket_num;

import org.quartz.*;

import dataOperation.DbController;
import dataOperation.JedisController;

public class WebJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {//执行的任务
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"); 
		System.out.println(sdf.format(new Date()));

		DbController dbController = new DbController();
		ArrayList<Ticket_num> list = new ArrayList<Ticket_num>();
		JedisController jedisController = JedisController.getJedisController();
		dbController.getNameAndNums(list);
		for(Ticket_num ticket_num : list){
			String movie_name = ticket_num.getMovieName();
			jedisController.updateDBTicketNum(movie_name);
			//jedisController.deleteFromRedis(movie_name);
		}

		Set<String> rush_Informations = jedisController.getAllRedis();
		for(String userName : rush_Informations){
			String userNum = jedisController.getUserRushTicketNum(userName);
			if(!userNum.matches("\\d+")){
				if(dbController.isCludeUserInRushInformation(userName) == false){//不包含
					dbController.insertRushInformation(new Rush_Information(userName,userNum));
				}else{//包含
					dbController.updateRushInformation(new Rush_Information(userName,userNum));
				}
			}
		}
	}

}
