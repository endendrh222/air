package com.air.entity;

import java.text.SimpleDateFormat;

import com.air.constant.OrderStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orders")
@ToString
@Getter
@Setter
public class Orders {
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private SimpleDateFormat orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guest_id")
	private Guest guest;
}
