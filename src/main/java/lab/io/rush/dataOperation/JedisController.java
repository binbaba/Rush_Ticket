package dataOperation;

import java.util.ArrayList;
import java.util.Set;

import model.Ticket_num;
import redis.clients.jedis.Jedis;
/*
 * 操作redis作为内存数据库
 */
public class JedisController {
    private Jedis jedis; 
	
	private JedisController(){
		jedis = new Jedis("127.0.0.1",6379);
		jedis.flushAll();//???????????
	}
	
	private static JedisController jedisController = null;
	
	public static JedisController getJedisController(){
		if(jedisController == null){
			synchronized(JedisController.class){
				if(jedisController == null){
					jedisController = new JedisController();
				}
			}
		}
		return jedisController;
	}
	
	public int insertTicketIntoRedis(){//将数据库中的票务信息存入Redis
		DbController dbController = new DbController();
		ArrayList<Ticket_num> list = new ArrayList<Ticket_num>();
		int num = dbController.getNameAndNums(list);
		for(Ticket_num ticket_num : list){
			jedis.set(ticket_num.getMovieName(), ticket_num.getTicketNum()+"");
		}
		return num;
	}
	
	public boolean insertDBUserTicketIntoRedis(String userName){//将单个用户的抢票信息存入Redis
		DbController dbController = new DbController();
		String userNum = dbController.getRushInformation(userName);
		if(userNum != null){
			jedis.set(userName, userNum);
			return true;
		}else return false;
	}
	
	public void insertUserTicketIntoRedis(String userName,String userNum){//将抢票信息存入Redis
		jedis.set(userName, userNum);
	}
	
	public void updateRedisTicketNum(String movieName,String ticketNum){//更新Redis中的电影票信息
		jedis.set(movieName, ticketNum);
	}
	
	public int getTicketNum(String movieName){//根据电影名获取到票数
		int num = -1;
		try{
		num = Integer.parseInt(jedis.get(movieName));
		}catch(NumberFormatException e){
			System.out.println("解析出问题");
		}
		return num;
	}
	
	public Set<String> getAllRedis(){//遍历整个redis的键
		return jedis.keys("*");
	}
	
	public String getUserRushTicketNum(String userName){//获得该用户的抢票信息
		return jedis.get(userName);
	}
	
	public boolean updateDBTicketNum(String movieName){//将符合名字的电影票更新到数据库
		DbController dbController = new DbController();
		int num = Integer.parseInt(jedis.get(movieName));
		return dbController.updateTicketNum(new Ticket_num(movieName,num));
	}
	
	public long deleteFromRedis(String name){//删除redis中符合键为name的键值对
		return jedis.del(name);
	} 

}
