package com.air.entity;

import com.air.constant.AirSellStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="airClass")
@ToString
@Getter
@Setter
public class AirClass {
	@Id
	@Column(name="air_class_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	private int economyPrice;
	
	private int businessPrice;
	
	private int firstPrice;
	
	@Enumerated(EnumType.STRING)
	private AirSellStatus economySellStatus;

	@Enumerated(EnumType.STRING)
	private AirSellStatus businessSellStatus;
	
	@Enumerated(EnumType.STRING)
	private AirSellStatus firstSellStatus;
		
	private int economyStockNumber;

	private int businessStockNumber;
	
	private int firstStockNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	
}
