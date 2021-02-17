package com.springweather.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.javabrains.springsecurity.jpa.models.UserCity;
import java.util.Optional;



@Repository
public interface CityRepository extends JpaRepository<UserCity,Integer>{
	Optional<UserCity> findByCityName(String cityName);
	
}