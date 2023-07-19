package com.air.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.air.entity.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long>, FlightRepositoryCustom {
	List<Flight> findByStartCountryName(String startCountryName); 
	
}
