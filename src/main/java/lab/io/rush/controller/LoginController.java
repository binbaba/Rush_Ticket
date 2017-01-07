package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataOperation.DbController;
import dataOperation.JedisController;

public class LoginController extends HttpServlet{
	/**
	 * 登录验证
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		System.out.println(name+":"+password);//??????????????
		
		DbController dbController = new DbController();
		boolean isUser = dbController.isUser(name, password);
		
		System.out.println(isUser);//???????????????
		
		if(isUser){
			String email = dbController.getEmail(name);
			HttpSession httpSession = request.getSession();
			HashMap<String,String> userInfo = new HashMap<String,String>();
			userInfo.put("userName", name);
			userInfo.put("email", email);
			httpSession.setAttribute("userInfo", userInfo);//保持会话
			
			JedisController jedisController = JedisController.getJedisController();
			jedisController.insertDBUserTicketIntoRedis(name);//将数据库中用户已存在的抢票信息加入数据库中。
		}else{
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(isUser);
			response.getWriter().flush();
			response.getWriter().close();
		}
	}
}
