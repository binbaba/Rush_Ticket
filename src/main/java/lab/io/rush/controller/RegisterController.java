package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataOperation.DbController;
import model.User;

public class RegisterController extends HttpServlet{
	/**
	 * 注册设置
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		if(!password.equals(repassword)){
			response.sendRedirect("./register.html");
			System.out.println("密码不正确"+password+","+repassword);
		}else{
			String name = request.getParameter("user_name");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			DbController dbController = new DbController();
			User user = new User(name,sex,email,password);
			if(dbController.insertUser(user)){
				response.sendRedirect("./index.html");
				System.out.println("存入成功");
				HttpSession httpSession = request.getSession();
				HashMap<String,String> userInfo = new HashMap<String,String>();
				userInfo.put("userName", name);
				userInfo.put("email", email);
				httpSession.setAttribute("userInfo", userInfo);
			}else{
				response.sendRedirect("./register.html");
				System.out.println("存入失败");
			}
		}
	}
}
