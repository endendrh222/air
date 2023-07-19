package com.air.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightSearchDto {
	private String searchDateType;
	private String searchBy;
	private String searchQuery = "";
}
