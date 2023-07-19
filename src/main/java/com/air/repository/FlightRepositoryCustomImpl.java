package com.air.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.air.dto.FlightSearchDto;
import com.air.dto.MainFlightDto;
import com.air.entity.Flight;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class FlightRepositoryCustomImpl implements FlightRepositoryCustom{

	private JPAQueryFactory queryfactory;
	
	public FlightRepositoryCustomImpl(EntityManager em) {
		this.queryfactory = new JPAQueryFactory(em);
	}
	

	@Override
	public Page<Flight> getAdminFlightPage(FlightSearchDto flightSearchDto, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MainFlightDto> getMainFlightPage(FlightSearchDto flightSearchDto, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
