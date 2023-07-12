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
	@Column(name="class_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String class_name;
	
	private int orderPrice;
	
	@Enumerated(EnumType.STRING)
	private AirSellStatus airSellStatus;
	
	private String airClassNumber;
	
	private int stockNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	
}
