package com.air.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.air.entity.AirClass;

public interface AirClassRepository extends JpaRepository<AirClass, Long> {
}
