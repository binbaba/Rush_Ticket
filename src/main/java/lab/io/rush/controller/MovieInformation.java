package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dataOperation.DbController;
import model.Movie;;

public class MovieInformation extends HttpServlet{
	/*
	 * 返回电影信息
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbController dbController = new DbController();
		ArrayList<Movie> list = new ArrayList<Movie>();
		int nums = dbController.getMovieInformation(list);
		
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i < nums;i++){
			Movie movie = list.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("movieName", movie.getMovieName());
			jsonObject.put("type", movie.getMovieType());
			jsonObject.put("director", movie.getMovieDirector());
			jsonObject.put("actors", movie.getMovieActors());
			jsonObject.put("plot", movie.getMoviePlot());
			jsonObject.put("pic", movie.getMoviePic());
			jsonArray.put(jsonObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("movieInformation", jsonArray);
		jsonObject.put("movieNum", nums);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(changeJson(jsonObject.toString()));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	private String changeJson(String json) {
		Pattern p = Pattern.compile("(\\\\u)[0-9A-Za-z]{4}");
		Matcher m = p.matcher(json);

		while(m.find()){
			String a = m.group();
			String x = a.replaceAll("\\\\u", "");
			char uni = (char)Integer.parseInt(x,16);			
			a = a.replaceAll("\\\\", "");
			json = json.replaceAll("\\\\"+a, uni+"");
		}
		return json;
	}
}
