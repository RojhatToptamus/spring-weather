package com.springweather.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder ()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	UserDetailsService userDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.antMatchers("/deleteCity/**","/weather/**").hasAnyRole("ADMIN","USER")
			.antMatchers(HttpMethod.POST, "/createUser").permitAll()
			.antMatchers("/","/createUser").permitAll()
			.and().formLogin().defaultSuccessUrl("/home", true);
		
			http.csrf().disable(); // kullanıcı eklerken hata veriyordu
	        http
	          .logout(logout -> logout
	            .logoutUrl("/home/logout")
	            .addLogoutHandler(new SecurityContextLogoutHandler())
	            .logoutSuccessUrl("/login")
	          );
	                	
	}
	


/*	
	@SuppressWarnings("deprecation")
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
*/	

}
