package model;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table="movie_information")
public class Movie {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;
	
	@Column(name="director")
	private String director;
	
	@Column(name="actors")
	private String actors;
	
	@Column(name="plot")
	private String plot;
	
	@Column(name="pic")
	private String pic;
	
	public Movie(String name,String type,String director,String actors,String plot,String pic){
		this.name = name;
		this.type = type;
		this.director = director;
		this.actors = actors;
		this.plot = plot;
		this.pic = pic;
	}

	public int getMovieId(){
		return id;
	}
	
	public String getMovieName(){
		return name;
	}
	
	public String getMovieType(){
		return type;
	}
	
	public String getMovieDirector(){
		return director;
	}
	
	public String getMovieActors(){
		return actors;
	}
	
	public String getMoviePlot(){
		return plot;
	}
	
	public String getMoviePic(){
		return pic;
	}
}
