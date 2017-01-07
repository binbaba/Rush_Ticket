package model;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table="rush_information")
public class Rush_Information {
	@PrimaryKey
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="user_num")
	private String user_num;
	
	public Rush_Information(String user_name,String user_num){
		this.user_name = user_name;
		this.user_num = user_num;
	}
	
	public void setUserNum(String user_num){
		this.user_num = user_num;
	}
	
	public String getUserName(){
		return user_name;
	}
	
	public String getUserNum(){
		return user_num;
	}

}
