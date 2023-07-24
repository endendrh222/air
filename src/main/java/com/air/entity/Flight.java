package com.air.entity;

import com.air.dto.FlightFormDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="flight")
@Getter
@Setter
@ToString
public class Flight extends BaseEntity{
	@Id
	@Column(name="flight_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String startCountryName;
	
	@Column(nullable = false)
	private String startDate;
	
	@Column(nullable = false)
	private String startTime;
	
	@Column(nullable = false)
	private String leaveCountryName;
	
	@Column(nullable = false)
	private String leaveDate;

	@Column(nullable = false)
	private String leaveTime;
	
	// flight 엔티티 업데이트
	public void updateFlight(FlightFormDto flightFormDto) {
		this.startCountryName = flightFormDto.getStartCountryName();
		this.startDate = flightFormDto.getStartDate();
		this.startTime = flightFormDto.getStartTime();
		this.leaveCountryName = flightFormDto.getLeaveCountryName();
		this.leaveDate = flightFormDto.getLeaveDate();
		this.leaveTime = flightFormDto.getLeaveTime();
	}
	
	public void deleteFlight(FlightFormDto flightFormDto) {
		deleteFlight(flightFormDto);
	}
}
