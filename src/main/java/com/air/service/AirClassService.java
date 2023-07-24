package com.air.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.air.dto.AirClassDto;
import com.air.entity.AirClass;
import com.air.repository.AirClassRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AirClassService {
	private final AirClassRepository airClassRepository;

	public Long saveAirClass(AirClassDto airClassDto) throws Exception {
		// 1.항공편 등록
		AirClass airClass = airClassDto.createAirClass();
		airClassRepository.save(airClass); // insert(저장)
		
		return airClass.getId();
		
	}
	
	
	@Transactional(readOnly = true) // 트랜잭션 읽기 전용(변경감지를 수행하지 않음) -> 성능향상
	public AirClassDto getAirClassDtl(Long AirClassId) {
		
		AirClass airClass = airClassRepository.findById(AirClassId).orElseThrow(EntityNotFoundException::new);
		
		AirClassDto airClassDto = AirClassDto.of(airClass);
		
		return airClassDto;
	}
}
