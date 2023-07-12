package com.air.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="airOrder")
@ToString
@Getter
@Setter
public class AirOrder {
	@Id
	@Column(name="orderAirId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "air_id")
	private Air air;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Orders orders;
}
