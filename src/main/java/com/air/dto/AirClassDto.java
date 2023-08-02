package com.air.dto;

import org.modelmapper.ModelMapper;

import com.air.constant.AirSellStatus;
import com.air.entity.AirClass;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirClassDto {
	private Long airClassId;
	
	@NotNull(message = "이코노미 좌석비용 입력값은 필수 입니다.")
	private int economyPrice;
	
	@NotNull(message = "비지니스 좌석비용 입력값은 필수 입니다.")
	private int businessPrice;
	
	@NotNull(message = "퍼스트 좌석비용 입력값은 필수 입니다.")
	private int firstPrice;
	
	@NotNull(message = "이코노미 남은좌석수 입력값은 필수 입니다.")
	private int economyStockNumber;

	@NotNull(message = "비지니스 남은좌석수 입력값은 필수 입니다.")
	private int businessStockNumber;
	
	@NotNull(message = "퍼스트 남은좌석수 입력값은 필수 입니다.")
	private int firstStockNumber;
	
	private AirSellStatus economySellStatus;
	
	private AirSellStatus businessSellStatus;
	
	private AirSellStatus firstSellStatus;
	
	
	public static ModelMapper modelMapper = new ModelMapper();
	
	public AirClass createAirClass() {
		return modelMapper.map(this, AirClass.class);				
	}
	
	public static AirClassDto of(AirClass airClass) {
		return modelMapper.map(airClass, AirClassDto.class);
	}
}
