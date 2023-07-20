package com.air.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.air.dto.FlightSearchDto;
import com.air.dto.MainFlightDto;
import com.air.dto.QMainFlightDto;
import com.air.entity.Flight;
import com.air.entity.QFlight;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class FlightRepositoryCustomImpl implements FlightRepositoryCustom{

	private JPAQueryFactory queryfactory;
	
	public FlightRepositoryCustomImpl(EntityManager em) {
		this.queryfactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now(); //현재 날짜, 시간
		
		if(StringUtils.equals("all", searchDateType) || searchDateType == null) 
			return null;
		else if(StringUtils.equals("1d", searchDateType))
			dateTime = dateTime.minusDays(1); //현재 날짜로부터 1일전
		else if(StringUtils.equals("1w", searchDateType))
			dateTime = dateTime.minusWeeks(1); //1주일 전
		else if(StringUtils.equals("1m", searchDateType))
			dateTime = dateTime.minusMonths(1); //1달 전
		else if(StringUtils.equals("6m", searchDateType))
			dateTime = dateTime.minusMonths(6); //6개월 전
			
		return QFlight.flight.regTime.after(dateTime); //Q객체 리턴
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("startCountryName", searchBy)) {
			//searchBy 가 itemNm이라면 -> 등록자로 검색 시
			return QFlight.flight.startCountryName.like("%" + searchQuery + "%"); //item_nm like %검색어%
		} else if(StringUtils.equals("createBy", searchBy)) {
			return QFlight.flight.createdBy.like("%" + searchQuery + "%"); //createBy like %검색어%
		}
		
		return null; //쿼리문을 실행하지 않는다.
	}
	
	

	@Override
	public Page<Flight> getAdminFlightPage(FlightSearchDto flightSearchDto, Pageable pageable) {
		List<Flight> content = queryfactory
				.selectFrom(QFlight.flight)
				.where(
						searchByLike(flightSearchDto.getSearchBy(), flightSearchDto.getSearchQuery()))
				.orderBy(QFlight.flight.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		//select count(*) from item where reg_time = ? and item_nm(create_by) like %검색어%;
		long total = queryfactory.select(Wildcard.count).from(QFlight.flight)
				.where(
						searchByLike(flightSearchDto.getSearchBy(), flightSearchDto.getSearchQuery()))
				.fetchOne();
					
		return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression startCountryNameLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? 
				null : QFlight.flight.startCountryName.like("%" + searchQuery + "%");
	}
	
	
	@Override
	public Page<MainFlightDto> getMainFlightPage(FlightSearchDto flightSearchDto, Pageable pageable) {
		// TODO Auto-generated method stub
		
		QFlight flight = QFlight.flight;
		
		List<MainFlightDto> content = queryfactory
				.select(
						new QMainFlightDto(
								flight.id,
								flight.startCountryName,
								flight.startDate,
								flight.leaveCountryName,
								flight.leaveDate)
						)
				.from(flight)
				.where(startCountryNameLike(flightSearchDto.getSearchQuery()))
				.orderBy(flight.id.desc())
				.orderBy(QFlight.flight.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryfactory
				.select(Wildcard.count)
				.from(flight)
				.where(startCountryNameLike(flightSearchDto.getSearchQuery()))
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total);
		
	}
	
}
