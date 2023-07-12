package com.air.entity;

import java.text.SimpleDateFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="flight")
@ToString
@Getter
@Setter
public class Flight {
	@Id
	@Column(name="flight_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int startCountryId;
	
	private String startCountryName;
	
	private SimpleDateFormat startDate;
	
	private SimpleDateFormat startTime;
	
	private int leaveCountryId;
	
	private String leaveCountryName;
	
	private SimpleDateFormat leaveDate;

	private SimpleDateFormat leaveTime;
}
