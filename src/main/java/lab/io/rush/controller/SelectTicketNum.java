package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dataOperation.JedisController;

public class SelectTicketNum extends HttpServlet{
	/**
	 * 返回票务信息
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JedisController jedisController = JedisController.getJedisController();
		String name = request.getParameter("names");
		String[] names = name.split(",");
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i < names.length;i++){
			int num = jedisController.getTicketNum(names[i]);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("movieName", names[i]);
			jsonObject.put("nums", num);
			jsonArray.put(jsonObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ticketInformation", jsonArray);
		jsonObject.put("movieNum", names.length);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonObject.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
}
