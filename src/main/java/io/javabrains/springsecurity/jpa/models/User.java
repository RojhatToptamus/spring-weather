 package io.javabrains.springsecurity.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.weather.*;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="app_user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private boolean active;
	private String role;
	//private String city;
	
	
	public User(String userName, boolean active, String role, String city) {
		super();
		this.userName = userName;
		this.active = active;
		this.role = role;
		//this.city = city;
	}
	
	//many to many 
   // @ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore 
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_cities", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"))
	private Collection<UserCity> usercities =  new ArrayList<UserCity>() ;
  
	
	public Collection<UserCity> getUsercities() {
		return usercities;
	}



	public void setUsercities(Collection<UserCity> usercities) {
		this.usercities = usercities;
	}
	
	//many to many end
	public User() {}

	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	/*public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	*/
	
}
