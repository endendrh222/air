package com.air.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="air")
@ToString
@Getter
@Setter
public class Air extends BaseEntity{
	@Id
	@Column(name="air_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int passenger;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "air_class_id")
	private AirClass airClass;
}
