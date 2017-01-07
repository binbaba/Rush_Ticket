package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataOperation.JedisController;
import emailOperation.EmailThreadPool;

public class RushTicketController extends HttpServlet{
	/**
	 * 抢票控制
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = "";
		request.setCharacterEncoding("utf-8");
		String rushName = request.getParameter("movieName");
		
		System.out.println("抢票电影名："+rushName);
		
		JedisController jedisController = JedisController.getJedisController();
		int num = jedisController.getTicketNum(rushName);
		if(num > 0){//有票
			HttpSession httpSession = request.getSession();
			if(httpSession.getAttribute("userInfo") == null){//登录出现问题
				str = "请重新登录！";
			}else{
				@SuppressWarnings("unchecked")
				HashMap<String,String> hashMap = (HashMap<String, String>) httpSession.getAttribute("userInfo");
				String userName = hashMap.get("userName");
				String userNum = jedisController.getUserRushTicketNum(userName);				
				if(userNum != null){
					if(userNum.matches(".*"+rushName+":\\d;.*")){//已抢到当前电影的票务						
							if(userNum.matches(".*"+rushName+":1;.*")){//匹配，代表只抢了一张票，可继续购票
								userNum = userNum.replaceFirst(rushName+":1;", "");
								userNum += rushName+":2;";
								jedisController.insertUserTicketIntoRedis(userName, userNum);
								jedisController.updateRedisTicketNum(rushName, (num - 1)+"");
								str = "恭喜您，抢票成功，请注意邮箱查收！";
								
								EmailThreadPool pool = EmailThreadPool.getEmailThreadPool();
								pool.sendEmail(userName, hashMap.get("email"), rushName);
							}else{//已抢到当前电影两张票
								str = "您已抢到两张电影票，不可再抢哦！";
							}
					}else{//未抢到当前电影的票务
						jedisController.insertUserTicketIntoRedis(userName, userNum+rushName+":1;");
						jedisController.updateRedisTicketNum(rushName, (num - 1)+"");
						str = "恭喜您，抢票成功，请注意邮箱查收！";
						EmailThreadPool pool = EmailThreadPool.getEmailThreadPool();
						pool.sendEmail(userName, hashMap.get("email"), rushName);
					}					
				}else{//不存在其他票务信息
					jedisController.insertUserTicketIntoRedis(userName, rushName+":1;");
					jedisController.updateRedisTicketNum(rushName, (num - 1)+"");
					str = "恭喜您，抢票成功，请注意邮箱查收！";
					EmailThreadPool pool = EmailThreadPool.getEmailThreadPool();
					pool.sendEmail(userName, hashMap.get("email"), rushName);
				}
			}
		}else{//票已抢光
			str = "大家太热情了，电影票已被抢光";			
		}
		
		System.out.println(str);//?????????????????????
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
