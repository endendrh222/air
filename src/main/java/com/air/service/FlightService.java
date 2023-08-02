package com.air.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.air.dto.FlightFormDto;
import com.air.dto.FlightSearchDto;
import com.air.dto.MainFlightDto;
import com.air.entity.Flight;
import com.air.repository.FlightRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {
	private final FlightRepository flightRepository;
	
	public Long saveFlight(FlightFormDto flightFormDto) throws Exception {
		// 1.항공편 등록
		Flight flight = flightFormDto.createFlight();
		flightRepository.save(flight); // insert(저장)
		
		return flight.getId();
		
	}
	
	@Transactional(readOnly = true) // 트랜잭션 읽기 전용(변경감지를 수행하지 않음) -> 성능향상
	public FlightFormDto getFlightDtl(Long FlightId) {
		
		// flight 테이블에 있는 데이터 가져오기
		Flight flight = flightRepository.findById(FlightId).orElseThrow(EntityNotFoundException::new);
		
		// flight entity 객체 -> dto로 변환
		FlightFormDto flightFormDto = FlightFormDto.of(flight);
		
		return flightFormDto;
	}
	
	
	
	//항공편 수정하기(update)
	public Long updateFlight(FlightFormDto flightFormDto) throws Exception {
		Flight flight = flightRepository.findById(flightFormDto.getId()).orElseThrow(EntityNotFoundException::new);
		
		//update 쿼리문 실행
		flight.updateFlight(flightFormDto);
		
		return flight.getId();
	}
	
	public void deleteFlight(Long flightId) {
		Flight flight = flightRepository.findById(flightId)
									    .orElseThrow(EntityNotFoundException::new);
		
		flightRepository.delete(flight);
	}
	
	
	@Transactional(readOnly = true)
	public Page<Flight> getAdminFlightPage(FlightSearchDto flightSearchDto, Pageable pageable ) {
		Page<Flight> flightPage = flightRepository.getAdminFlightPage(flightSearchDto, pageable);
		return flightPage;
	}
	
	@Transactional(readOnly = true)
	public Page<MainFlightDto> getMainFlightPage(FlightSearchDto flightSearchDto, Pageable pageable) {
		Page<MainFlightDto> mainFlightPage = flightRepository.getMainFlightPage(flightSearchDto, pageable);
		return mainFlightPage;
	}
	
}
