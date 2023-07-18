package com.air.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.air.dto.FlightSearchDto;
import com.air.dto.MainFlightDto;
import com.air.entity.Flight;

public interface FlightRepositoryCustom {
	Page<Flight> getAdminFlightPage(FlightSearchDto flightSearchDto, Pageable pageable);
	
	Page<MainFlightDto> getMainFlightPage(FlightSearchDto flightSearchDto, Pageable pageable);
}
