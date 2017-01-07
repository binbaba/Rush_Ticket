package model;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table="ticket_number")
public class Ticket_num {
	@PrimaryKey
	@Column(name="movie_name")
	private String movie_name;
	
	@Column(name="number")
	private int number;
	
	public Ticket_num(String movie_name,int number){
		this.movie_name = movie_name;
		this.number = number;
	}
	
	public String getMovieName(){
		return movie_name;
	}
	
	public void setTicketNum(int number){
		this.number = number;
	}
	
	public int getTicketNum(){
		return number;
	}
}
