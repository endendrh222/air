package com.air.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.multipart.MultipartFile;

import com.air.dto.AirClassDto;
import com.air.dto.FlightFormDto;
import com.air.dto.FlightSearchDto;
import com.air.dto.MainFlightDto;
import com.air.entity.Flight;
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
		Page<MainFlightDto> flight = flightService.getMainFlightPage(flightSearchDto, pageable);
		
		model.addAttribute("flight", flight);
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
	

	//항공편 관리
	
		@GetMapping(value = {"/admin/flights", "/admin/flights/{page}"})
		public String flightManage(FlightSearchDto flightSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
			
			// url 경로에 페이지가 있으면 해당 페이지 번호를 조회하도록하고, 페이지 번호가 없으면 0 페이지를 조회
			Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 3); // of(조회할 페이지의 번호: 0부터 시작, 한 페이지 당 조회할 데이터의 갯수)
			
			Page<Flight> flights = flightService.getAdminFlightPage(flightSearchDto, pageable);
			
			model.addAttribute("flights", flights);
			model.addAttribute("flightSearchDto", flightSearchDto);
			model.addAttribute("maxPage", 5);
			
			return "flight/flightMng";
		}
		
		//항공편 수정페이지 보여주기
		@GetMapping(value = "/admin/flight/{flightId}")
		public String flightDtl(@PathVariable("flightId") Long flightId, Model model ) {
			try {
				FlightFormDto flightFormDto = flightService.getFlightDtl(flightId);
				model.addAttribute("flightFormDto", flightFormDto);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage","항공편 정보를 가져올때 에러가 발생했습니다.");
				
				model.addAttribute("flightFormDto", new FlightFormDto());
				return "flight/flightForm";
			}
			return "flight/flightModifyForm";
		}
	
	
		
		
	
	// 항공편 등록(insert)
	
	@PostMapping(value = "/admin/flight/new")
	public String flightUpdate(@Valid FlightFormDto flightFormDto,  BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "flight/flightForm";
		}
		
		try {
			flightService.saveFlight(flightFormDto);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "항공편 등록 중 에러가 발생했습니다.");
			return "flight/flightForm";
		}
		return "redirect:/";
	}
	
	
	
	//항공편 수정(update)
	@PostMapping(value = "/admin/flight/{flightId}")
	public String flightUpdate(@Valid FlightFormDto flightFormDto, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "flight/flightForm";
		}
		
		try {
			flightService.updateFlight(flightFormDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage" , "항공편 수정 중 에러가 발생했습니다.");
			return "flight/flightForm";
		}
		return "redirect:/";
	}
	
	//항공편 삭제
	@DeleteMapping(value = "/admin/{flightId}/delete")
	public @ResponseBody ResponseEntity flightDelete(@RequestBody @PathVariable("flightId") Long flightId,
				Principal principal) {
		
		
		try {
			flightService.deleteFlight(flightId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		
		return new ResponseEntity<Long>(flightId, HttpStatus.OK);
	}
	
	

	
}
