package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rush_Information;
import dataOperation.DbController;
import dataOperation.JedisController;

public class QuitController extends HttpServlet{
	/**
	 * 用户退出设置
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		HashMap<String,String> hashMap = (HashMap<String,String>)request.getSession().getAttribute("userInfo");
		String userName = hashMap.get("userName");
		request.getSession().removeAttribute("userInfo");//删除会话
		JedisController jedisController = JedisController.getJedisController();
		String userNum = jedisController.getUserRushTicketNum(userName);
		if(userNum != null){
			DbController dbController = new DbController();
			if(dbController.isCludeUserInRushInformation(userName) == false){//不包含
				dbController.insertRushInformation(new Rush_Information(userName,userNum));
				System.out.println("不包含");
			}else{//包含
				dbController.updateRushInformation(new Rush_Information(userName,userNum));
				System.out.println("包含");
			}
			jedisController.deleteFromRedis(userName);
		}
	}
}
