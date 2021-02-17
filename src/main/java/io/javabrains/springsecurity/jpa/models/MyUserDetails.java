package io.javabrains.springsecurity.jpa.models;

import java.util.List;
import com.spring.weather.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails{

	private String userName;
	private String password;
	//private String city;
	private boolean active;
	
	private  List<GrantedAuthority> authorities;

	public MyUserDetails(User user) {
		
		this.userName=user.getUserName();
		this.password = user.getPassword();
		this.active = user.isActive();
		
		this.authorities = Arrays.stream(user.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	public MyUserDetails() {	
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}
	/*public String getCity() {
		// TODO Auto-generated method stub
		return city;
	}*/
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}
	
}
