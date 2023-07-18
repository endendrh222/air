package com.air.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.air.dto.FlightFormDto;
import com.air.dto.FlightSearchDto;
import com.air.dto.MainFlightDto;
import com.air.service.FlightService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FlightController {
	
	private final FlightService flightService;
	
	// 항공편 전체 리스트
	@GetMapping(value = "/flight/air")
	public String flightAirList(Model model, FlightSearchDto flightSearchDto, Optional<Integer> page) {
		System.out.println(flightSearchDto);
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6); // of(조회할 페이지의 번호 , 한 페이지 당 조회할 데이터 개수)
		Page<MainFlightDto> flights = flightService.getMainFlightPage(flightSearchDto, pageable);
		
		model.addAttribute("flights", flights);
		model.addAttribute("flightSearchDto", flightSearchDto);
		model.addAttribute("maxPage", 5);
		
		return "flight/flightAirList";
	}
	
	// 항공권 상세 페이지
	@GetMapping(value = "/flight/{flightId}")
	public String flightDtl(Model model, @PathVariable("flightId") Long flightId) {
		FlightFormDto flightFormDto = flightService.getFlightDtl(flightId);
		model.addAttribute("flight", flightFormDto);
		
		return "flight/flightDtl";
	}
	
	// 항공편 등록 화면페이지
	@GetMapping(value = "/admin/flight/new")
	public String flightForm(Model model) {
		model.addAttribute("flightFormDto", new FlightFormDto());
		return "flight/flightForm";
	}
	
	@PostMapping(value = "/admin/flight/{flightId}")
	public String flightUpdate(@Valid FlightFormDto flightFormDto, Model model, BindingResult bindingResult, @RequestParam("flightImgFile") List<MultipartFile> flightImgFileList) {
		if(bindingResult.hasErrors()) {
			return "flight/flightForm";
		}
		
		try {
			flightService.updateFlight(flightFormDto, flightImgFileList);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "항공편 수정 중 에러가 발생했습니다.");
			return "flight/flightForm";
		}
		return "redirect:/";
	}
}
