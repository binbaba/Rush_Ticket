package dataOperation;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import model.Movie;
import model.Rush_Information;
import model.Ticket_num;
import model.User;

public class DbController {
	private PersistenceManagerFactory pmf;

	public DbController(){
		pmf = JDOHelper.getPersistenceManagerFactory("Rush_Ticket");
	}
	
	public String getEmail(String name){//获取数据库中用户名为name的邮箱
		if(name == null) return "error";
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+User.class.getName()+" where name==\""+name+"\"");
			@SuppressWarnings("unchecked")
			List<User> user = (List<User>)query.execute();
			tx.commit();
			if(user.size() == 0) return "error";
			else {
				return user.get(0).getUserEmail();
			}
		} catch (Exception e) {
			System.out.println("数据库错误");
			return "error";
		}finally{
			pm.close();
		}
	}

	public boolean insertUser(User user){//往数据库中存入User
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(user);
			tx.commit();
			return true;
		} catch (Exception e) {
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}
	
	public boolean insertRushInformation(Rush_Information rush_Information){//往数据库中存入Rush_Informtion
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(rush_Information);
			tx.commit();
			return true;
		} catch (Exception e) {
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}
	
	public boolean isCludeUserName(String name){//检查user_information数据库是否包含该name
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+User.class.getName()+" where name==\""+name+"\"");//查询时要跟对象中的名字相同，对象的属性代替了数据库中的列名。
			@SuppressWarnings("unchecked")
			List<User> user = (List<User>)query.execute();
			tx.commit();
			if(user.size()==0) return false;
			else return true;
		} catch (Exception e) {
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}
	
	public boolean insertMovie(Movie movie){//往数据库中插入电影
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(movie);
			tx.commit();
			return true;
		} catch (Exception e) {
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}
	
	public boolean updateTicketNum(Ticket_num ticket_num){//更新数据库ticket_number中的票数
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("javax.jdo.query.SQL","update ticket_number set number="+ticket_num.getTicketNum()+" where movie_name=\""+ticket_num.getMovieName()+"\"");
			query.execute();
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}
	
	public boolean updateRushInformation(Rush_Information rush_information){//更新数据库rush_information中的票数
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("javax.jdo.query.SQL","update rush_information set user_num=\""+rush_information.getUserNum()+"\" where user_name=\""+rush_information.getUserName()+"\"");
			query.execute();
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}

	public boolean isUser(String name,String password){//登录时检查用户名与密码
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+User.class.getName()+" where name==\""+name+"\" && password==\""+password+"\"");
			@SuppressWarnings("unchecked")
			List<User> user = (List<User>)query.execute();
			tx.commit();
			if(user.size() == 0) return false;
			else return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}

	public int getNameAndNums(ArrayList<Ticket_num> list){//获取数据库ticket_number中电影名与票数
		int nums = 0;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+Ticket_num.class.getName());
			@SuppressWarnings("unchecked")
			List<Ticket_num> ticket_nums = (List<Ticket_num>) query.execute();
			nums = ticket_nums.size();
			list.addAll(ticket_nums);
			tx.commit();
		}catch(Exception e){
			System.out.println("数据库错误");
			if(tx.isActive()) tx.rollback();
		}finally{
			pm.close();
		}		
		return nums;
	}
	
	public int getMovieInformation(ArrayList<Movie> list){//获取数据库movie_information中的电影
		int nums = 0;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+Movie.class.getName());
			@SuppressWarnings("unchecked")
			List<Movie> movies = (List<Movie>) query.execute();
			nums = movies.size();
			list.addAll(movies);
			tx.commit();
		}catch(Exception e){
			System.out.println("数据库错误");
			if(tx.isActive()) tx.rollback();
		}finally{
			pm.close();
		}		
		return nums;
	}
	
	public int getRushInformation(ArrayList<Rush_Information> list){//获取数据库rush_information中的抢票信息
		int nums = 0;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+Rush_Information.class.getName());
			@SuppressWarnings("unchecked")
			List<Rush_Information> rush_Informations = (List<Rush_Information>) query.execute();
			nums = rush_Informations.size();
			list.addAll(rush_Informations);
			tx.commit();
		}catch(Exception e){
			System.out.println("数据库错误");
			if(tx.isActive()) tx.rollback();
		}finally{
			pm.close();
		}		
		return nums;
	}
	
	public String getRushInformation(String user_name){//获取数据库rush_information中单个用户的抢票信息
		String user_num = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+Rush_Information.class.getName()+" where user_name==\""+user_name+"\"");
			@SuppressWarnings("unchecked")
			List<Rush_Information> rush_Informations = (List<Rush_Information>) query.execute();
			tx.commit();
			if(rush_Informations.size() != 0){
				user_num = rush_Informations.get(0).getUserNum();
			}
		}catch(Exception e){
			System.out.println("数据库错误");
			if(tx.isActive()) tx.rollback();
		}finally{
			pm.close();
		}		
		return user_num;
	}
	
	public boolean isCludeUserInRushInformation(String userName){//表rush_information中是否已存在该用户
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+Rush_Information.class.getName()+" where user_name==\""+userName+"\"");//查询时要跟对象中的名字相同，对象的属性代替了数据库中的列名。
			@SuppressWarnings("unchecked")
			List<User> user = (List<User>)query.execute();
			tx.commit();
			if(user.size()==0) return false;//不包含
			else return true;//包含
		} catch (Exception e) {
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}

	public boolean isCludeUserEmail(String email) {//邮箱是否已注册
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("select from "+User.class.getName()+" where email==\""+email+"\"");//查询时要跟对象中的名字相同，对象的属性代替了数据库中的列名。
			@SuppressWarnings("unchecked")
			List<User> user = (List<User>)query.execute();
			tx.commit();
			if(user.size()==0) return false;
			else return true;
		} catch (Exception e) {
			System.out.println("数据库错误");
			return false;
		}finally{
			pm.close();
		}
	}
}
