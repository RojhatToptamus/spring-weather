package io.javabrains.springsecurity.jpa.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="user_city")
public class UserCity implements Serializable{
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)	
	private int id;
	
	private String cityName;
	
    ////many to many
	@ManyToMany(mappedBy = "usercities",fetch = FetchType.LAZY)
	private Collection<User> users;	

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	//many to many end

	public UserCity() {}
	

	public UserCity( String cityName) {
		super();		
		this.cityName = cityName;
		
	}
	

	public int getCityId() {
		return id;
	}

	public void getCityId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	
	
}
