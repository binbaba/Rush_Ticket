package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IsLogined extends HttpServlet{
	/**
	 * 判断用户是否已登录
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = "";
		HttpSession httpSession = request.getSession();
		if(httpSession.getAttribute("userInfo") == null){
			str = "false";
		}else{
			@SuppressWarnings("unchecked")
			HashMap<String,String> hashMap = (HashMap<String, String>) httpSession.getAttribute("userInfo");
			str += hashMap.get("userName");
		}
		
		System.out.println(str);//?????????????????????用户名或false
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
