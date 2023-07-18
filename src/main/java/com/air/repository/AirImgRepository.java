package com.air.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.air.entity.AirImg;

public interface AirImgRepository extends JpaRepository<AirImg, Long>{
	
	List<AirImg> findByAirIdOrderByIdAsc(Long airId);
	
	AirImg findByAirIdAndRepimgYn(Long AirId, String repimgYn);
}
