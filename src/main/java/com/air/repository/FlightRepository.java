package com.air.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.air.entity.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long>, FlightRepositoryCustom {
	List<Flight> findByStartCountryName(String startCountryName); 
	
	@Query("select o from Flight o")
	List<Flight> findFlights(@Param("email") String email, Pageable pageable);
	
}
