package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataOperation.DbController;

public class IsCludeUserName extends HttpServlet{
	/**
	 * 注册时使用的用户名是否已存在
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbController dbController = new DbController();
		String name = request.getParameter("user_name");
		boolean isCludeUser = dbController.isCludeUserName(name);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(isCludeUser);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
