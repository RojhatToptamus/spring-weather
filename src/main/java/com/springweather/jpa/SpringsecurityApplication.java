package com.springweather.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.spring.weather.WeatherService;
import io.javabrains.springsecurity.jpa.models.User;
import io.javabrains.springsecurity.jpa.models.UserCity;
import org.hibernate.Session;

@EnableAutoConfiguration

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = { UserRepository.class,CityRepository.class })

public class SpringsecurityApplication implements CommandLineRunner{
    @Bean
    public WeatherService ws() {
        return new WeatherService ();
    }
    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserRepository userRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Application Running.");	

        User adminUser= new User();          
        adminUser.setUserName("Admin");
        adminUser.setPassword(new BCryptPasswordEncoder().encode("pass"));
        adminUser.setRole("ROLE_ADMIN");
        adminUser.setActive(true);
        //adminUser.setCity("istanbul");
        this.userRepository.save(adminUser);
        
		UserCity ucity = new UserCity("bursa");
		UserCity ucity2 = new UserCity("bolu");
		UserCity ucity3 = new UserCity("denizli");
		UserCity ucity4 = new UserCity("sivas");
		UserCity ucity5 = new UserCity("istanbul");
        
		cityRepository.save(ucity);
		cityRepository.save(ucity2);
		cityRepository.save(ucity3);
		cityRepository.save(ucity4);
		cityRepository.save(ucity5);
		
        adminUser.getUsercities().add(ucity);
        adminUser.getUsercities().add(ucity2);
        
        userRepository.save(adminUser);
        
        User newUser= new User();
        newUser.setUserName("User");
        newUser.setPassword(new BCryptPasswordEncoder().encode("pass"));
        newUser.setRole("ROLE_USER");
        newUser.setActive(true);
        //newUser.setCity("izmir");
        this.userRepository.save(newUser);

        newUser.getUsercities().add(ucity4);
       
        userRepository.save(newUser);

      

        

	}

}
