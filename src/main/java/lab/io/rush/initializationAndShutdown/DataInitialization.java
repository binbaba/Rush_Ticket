package initializationAndShutdown;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dataOperation.DbController;
import dataOperation.JedisController;
import model.Rush_Information;
import model.Ticket_num;

public class DataInitialization implements ServletContextListener{
	/*
	 * 初始化
	 */

	@Override
	public void contextInitialized(ServletContextEvent sce) {//提前将数据库中信息加入Redis中
		// TODO Auto-generated method stub
		JedisController jedisController = JedisController.getJedisController();
		jedisController.insertTicketIntoRedis();
		Task task = new Task();
		task.go();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {//关闭前将数据存入数据库
		DbController dbController = new DbController();
		ArrayList<Ticket_num> list = new ArrayList<Ticket_num>();
		JedisController jedisController = JedisController.getJedisController();
		dbController.getNameAndNums(list);
		for(Ticket_num ticket_num : list){
			String movie_name = ticket_num.getMovieName();
			jedisController.updateDBTicketNum(movie_name);
			jedisController.deleteFromRedis(movie_name);
		}
		
		Set<String> rush_Informations = jedisController.getAllRedis();
		for(String userName : rush_Informations){
			String userNum = jedisController.getUserRushTicketNum(userName);
			if(dbController.isCludeUserInRushInformation(userName) == false){//不包含
				dbController.insertRushInformation(new Rush_Information(userName,userNum));
			}else{//包含
				dbController.updateRushInformation(new Rush_Information(userName,userNum));
			}
			jedisController.deleteFromRedis(userName);
		}
		System.out.println("结束");
	}

}
