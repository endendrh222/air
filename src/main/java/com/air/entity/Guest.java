package com.air.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="guest")
@ToString
@Getter
@Setter
public class Guest {
	@Id
	@Column(name="guest_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int passportNumber;
	
	private int phoneNumber;
}
