package com.air.dto;


import org.modelmapper.ModelMapper;

import com.air.entity.Flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightFormDto {
	
	private Long id;
	
	@NotBlank(message = " 출발국가는 필수 입력값 입니다.")
	private String startCountryName;
	
	@NotNull(message = " 출발일은 필수 입력값 입니다.")
	private String startDate;
	
	@NotNull(message = " 출발시간은 필수 입력값 입니다.")
	private String startTime;
	
	@NotBlank(message = " 도착국가는 필수 입력값 입니다.")
	private String leaveCountryName;
	
	@NotNull(message = " 도착일 필수 입력값 입니다.")
	private String leaveDate;
	
	@NotNull(message = " 도착시간은 필수 입력값 입니다.")
	private String leaveTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Flight createFlight() {
		return modelMapper.map(this, Flight.class);
	}
	
	public static FlightFormDto of(Flight flight) {
		return modelMapper.map(flight, FlightFormDto.class);
	}
}
