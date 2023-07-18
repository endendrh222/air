package com.air.dto;

import java.text.SimpleDateFormat;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainFlightDto {

	private Long id;
	
	private String startCountryName;
	
	private SimpleDateFormat startDate;
	
	private String leaveCountryName;
	
	private SimpleDateFormat leaveDate;
	
	@QueryProjection
	public MainFlightDto(Long id, String startCountryName, SimpleDateFormat startDate, String leaveCountryName, SimpleDateFormat leaveDate) {
		this.id = id;
		this.startCountryName = startCountryName;
		this.startDate = startDate;
		this.leaveCountryName = leaveCountryName;
		this.leaveDate = leaveDate;
	}
}
