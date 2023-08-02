package com.air.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.air.dto.AirClassDto;
import com.air.dto.FlightFormDto;
import com.air.service.AirClassService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AirClassController {
	private final AirClassService airClassService;
	
	
	//좌석 등록 및 수정 페이지
	@GetMapping(value = "/admin/airClass/{airClassId}")
	public String airClassDtl(@PathVariable("airClassId") Long airClassId, Model model) {
	
		try {
			AirClassDto airClassDto = airClassService.getAirClassDtl(airClassId);
			model.addAttribute("airClassDto", airClassDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage","좌석 정보를 가져올때 에러가 발생했습니다.");
			
			model.addAttribute("airClassDto", new AirClassDto());
			return "flightclass/airClass";
		}
		return "flightclass/airClassModifyForm";
	}
	
	//좌석 등록
	@PostMapping(value = "/admin/airClass/new")
	public String airClassUpdate(@Valid AirClassDto airClassDto,  BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "flightclass/airClass";
		}
		
		try {
			airClassService.saveAirClass(airClassDto);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "좌석 등록 중 에러가 발생했습니다.");
			return "flightclass/airClassModifyForm";
		}
		return "redirect:/";
	}
	
			
			//좌석 수정(update)
			@PostMapping(value = "/admin/airClass/{airClassId}")
			public String airClassUpdate(@Valid AirClassDto airClassDto, Model model, BindingResult bindingResult) {
				if(bindingResult.hasErrors()) {
					return "flightclass/airClassModifyForm";
				}
				
				
				try {
					airClassService.updateAirClass(airClassDto);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("errorMessage" , "항공편 수정 중 에러가 발생했습니다.");
					return "flightclass/airClassModifyForm";
				}
				return "redirect:/";
			}
			
			
			//항공편 삭제
			@DeleteMapping(value = "/admin/airClass/{airClassId}/delete")
			public @ResponseBody ResponseEntity airClassDelete(@RequestBody @PathVariable("airClassId") Long airClassId,
						Principal principal) {
				
				
				try {
					airClassService.deleteAirClass(airClassId);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				
				
				return new ResponseEntity<Long>(airClassId, HttpStatus.OK);
			}
				
			
			
}
