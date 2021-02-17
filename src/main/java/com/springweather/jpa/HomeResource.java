package com.springweather.jpa;

import java.security.Principal;
import com.spring.weather.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import io.javabrains.springsecurity.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.weather.WeatherService;
import com.springweather.jpa.UserRepository;
import com.sun.security.auth.UserPrincipal;

import io.javabrains.springsecurity.jpa.models.*;



@RestController
public class HomeResource {
	public int userID;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private UserRepository userRepo;
	
    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("Welcome User");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");

    }
    @GetMapping("/getCities")
    public List<UserCity> getCities()
    {
    	return cityRepository.findAll();
    }

    @GetMapping("/admin/getUsers")
    public List<User> getAllUsers(){
    	return userRepo.findAll();
    }
    @GetMapping("/admin/getUser/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable int id) {
       Optional<User> optionalUser = userRepo.findById(id);
       if (!optionalUser.isPresent()) {
          return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
       } else {
         return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
       }
    }
	@GetMapping("/addCity/{city}")
    public  ResponseEntity<String> addCityToUser(@PathVariable String city) {
		int id=userID;
    	Optional<User> foundUser;
		foundUser= userRepo.findById(id);
		if(!foundUser.isPresent()) {
			return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
		}
		else {
		List<UserCity> foundCities = new ArrayList<UserCity>();	
				Optional<UserCity> foundCity = cityRepository.findByCityName(city);
				if (foundCity.isPresent()) {
					Collection<User> hadUser=foundCity.get().getUsers();
					if(hadUser.contains(foundUser.get())) {
						return new ResponseEntity<>("User already has that city",HttpStatus.NOT_FOUND);
					}
					else {
						foundUser.get().getUsercities().add(foundCity.get());
						userRepo.save(foundUser.get());
						return new ResponseEntity<>("New City Added to User ",HttpStatus.OK);	
					}
					
				}
				else {
					return new ResponseEntity<>("City name is wrong",HttpStatus.NOT_FOUND);
				}
		}
		
	}    
  
    
    @GetMapping("/deleteCity/{city}")
	public ResponseEntity<String> deleteUserCity( @PathVariable String city)  
	{  
    	int id=userID;
    	Optional<User> foundUser= userRepo.findById(id);   	
		if(foundUser.get()==null) {
	     return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
		}
		else {
		  Optional<UserCity> foundCity = cityRepository.findByCityName(city);
		  if(foundCity.get().getUsers().contains(foundUser.get())) {
			   foundUser.get().getUsercities().remove(foundCity.get());	
			   userRepo.save(foundUser.get());
			   return new ResponseEntity<>("City Removed",HttpStatus.OK);
		  }
		  else {
			 	return new ResponseEntity<>("City Not Found for the User",HttpStatus.NOT_FOUND);
		  }  
		}       
	}    
 
    @PostMapping("/admin/createUser")
    public ResponseEntity<String> createUser(@RequestBody User myuser){
    	Optional<User> existUser= userRepo.findByUserName(myuser.getUserName());
    	if(existUser.isPresent()){
    		return new ResponseEntity<>("User Already Exist", HttpStatus.BAD_REQUEST);
    	}
    	else{
    		 myuser.setPassword(bcryptPasswordEncoder.encode(myuser.getPassword()));
    	      User savedUser=userRepo.save(myuser);
    	      return new ResponseEntity<>("New User Added", HttpStatus.OK);
    	}
    		
    } 
    @PutMapping("/admin/updateUsername/{id}")  
    public ResponseEntity<String> updateUsername(@PathVariable int id,@RequestBody String newUserName)  
    {  
    	Optional<User> existUser= userRepo.findById(id);
    	if(!existUser.isPresent()){
    		return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    	}
		else{
			existUser.get().setUserName(newUserName);
			userRepo.save(existUser.get());
			return new ResponseEntity<>("Username Updated", HttpStatus.OK);
		}
    } 
    
    @PutMapping("/admin/updateUserRole/{id}")  
    public ResponseEntity<String> updateUserRole(@PathVariable int id,@RequestBody String newUserRole)  
    {  
    	Optional<User> existUser= userRepo.findById(id);
    	if(!existUser.isPresent()){
    		return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    	}
		else{
			existUser.get().setRole(newUserRole);
			userRepo.save(existUser.get());
			return new ResponseEntity<>(existUser.get().getUserName()+" Assigned As "+newUserRole, HttpStatus.OK);
		}
    } 
 
    @DeleteMapping("/admin/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id)  {
    	Optional<User> foundUser;
		foundUser= userRepo.findById(id);
		if(!foundUser.isPresent()) {
	     return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
		}
		else {
			 userRepo.deleteById(foundUser.get().getId());
			 return new ResponseEntity<>("user removed",HttpStatus.OK);
		}
		 
		  //userRepo.delete(foundUser.get());		
	}


	/*@RequestMapping("/weatherID/{id}")
	public ResponseEntity<Root> returnMain(@PathVariable int id) {
		User loggedUser=userRepo.getOne(id);
		String city=loggedUser.getCity();
		weatherService.setCityName(city);		
	    return new ResponseEntity<Root>(weatherService.getWeather(),HttpStatus.OK);
	
	}*/
	@RequestMapping("/weather/{city}")
	public ResponseEntity<Root> returnWeather(@PathVariable String city) {
		
		User loggedUser=userRepo.getOne(userID);
		Collection<UserCity> cities = loggedUser.getUsercities();
		
		int size= cities.size();
		
		for(int i=0; i<=size;i++) {
			if(((List<UserCity>) cities).get(i).getCityName().equals(city)) {
				weatherService.setCityName(city);
				   return new ResponseEntity<Root>(weatherService.getWeather(),HttpStatus.OK);
			}

		}
				
	    return new ResponseEntity<Root>(weatherService.getWeather(),HttpStatus.OK);
	
	}
	@RequestMapping("/home")
	public String returnUser(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = ((UserDetails)principal).getUsername();
		Optional<User> user=userRepo.findByUserName(username);
		
		userID=user.get().getId();
	 	String a="";
			User loggedUser=userRepo.getOne(userID);
			Collection<UserCity> cities = loggedUser.getUsercities();
			for(int i=0;i<cities.size();i++) {
				a=a+((List<UserCity>) cities).get(i).getCityName()+",";
			}
			return "User Id: "+loggedUser.getId()+"\n"+"Username: "+loggedUser.getUserName()+"\n"+
			" User Role: "+loggedUser.getRole()+"\n"
			+": "+a;
	}
   
}


