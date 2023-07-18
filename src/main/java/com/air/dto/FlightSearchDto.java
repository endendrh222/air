package com.air.dto;

import com.air.constant.AirSellStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightSearchDto {
	private String searchDateType;
	private AirSellStatus searchSellStatus; 
	private String searchBy;
	private String searchQuery = "";
}
