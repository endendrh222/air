package com.air.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.air.dto.AirClassDto;
import com.air.dto.FlightSearchDto;
import com.air.entity.Flight;
import com.air.service.AirClassService;
import com.air.service.FlightService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AirController {
	
	private final FlightService flightService;
	private final AirClassService airClassService;
	
	@GetMapping(value = "/air/airOrder")
	public String airOrder(Model model, FlightSearchDto flightSearchDto, @PathVariable("page") Optional<Integer> page, AirClassDto airClassDto) {
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 3); // of(조회할 페이지의 번호: 0부터 시작, 한 페이지 당 조회할 데이터의 갯수)
		
		Page<Flight> flights = flightService.getAdminFlightPage(flightSearchDto, pageable);
		
		
		model.addAttribute("flights", flights);
		model.addAttribute("flightSearchDto", flightSearchDto);
		model.addAttribute("maxPage", 5);
		
		
		return "/air/airOrder";
	}
}
