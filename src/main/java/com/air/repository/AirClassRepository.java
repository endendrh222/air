package com.air.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.air.entity.AirClass;

public interface AirClassRepository extends JpaRepository<AirClass, Long> {
	
	@Query("select count(o) from AirClass o where o.flightId = :flightId")
	Long countOrder(@Param("flightId") Long flightId);

}
