package com.springweather.jpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurity.jpa.models.MyUserDetails;
import io.javabrains.springsecurity.jpa.models.User;



@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: "+userName));
		
		return user.map(MyUserDetails::new).get();
	}

}
