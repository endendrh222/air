package com.air.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.air.dto.AirClassDto;
import com.air.service.AirClassService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AirClassController {
	private final AirClassService airClassService;
	
	
	//좌석 등록/수정 페이지
	@GetMapping(value = "/admin/airClass/{airClassId}")
	public String airClassDtl(@PathVariable("airClassId") Long airClassId, Model model ) {
		try {
			AirClassDto airClassDto = airClassService.getAirClassDtl(airClassId);
			model.addAttribute("airClassDto", airClassDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage","항공편 정보를 가져올때 에러가 발생했습니다.");
			
			model.addAttribute("airClassDto", new AirClassDto());
			return "flightclass/airClass";
		}
		return "flightclass/airClassModifyForm";
	}
	
	
	@PostMapping(value = "/admin/airClass/new")
	public String airClassUpdate(@Valid AirClassDto airClassDto,  BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "flightclass/airClass";
		}
		
		try {
			airClassService.saveAirClass(airClassDto);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "항공편 등록 중 에러가 발생했습니다.");
			return "flightclass/airClassModifyForm";
		}
		return "redirect:/";
	}
}
