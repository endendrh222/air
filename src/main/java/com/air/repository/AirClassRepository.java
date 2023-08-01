package com.air.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.air.entity.AirClass;

public interface AirClassRepository extends JpaRepository<AirClass, Long> {
	@Query("select o from AirClass o")
	List<AirClass> findAirClass(@Param("airClassId") String airClassId);
}
