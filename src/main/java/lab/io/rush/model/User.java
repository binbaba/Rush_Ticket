package model;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table="user_information")
public class User {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="email_address")
	private String email;
	
	@Column(name="password")
	private String password;
	
	public User(String name,String sex,String email,String password){
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.password = password;
	}
	
	public void setUserId(int id){
		this.id = id;
	}
	
	public void setUserName(String name){
		this.name = name;
	}
	
	public void setUserSex(String sex){
		this.sex = sex;
	}
	
	public void setUserEmail(String email){
		this.email = email;
	}
	
	public void setUserPassword(String password){
		this.password = password;
	}
	
	public int getUserId(){
		return id;
	}
	
	public String getUserName(){
		return name;
	}
	
	public String getUserSex(){
		return sex;
	}
	
	public String getUserEmail(){
		return email;
	}
	
	public String getUserPassword(){
		return password;
	}
}
