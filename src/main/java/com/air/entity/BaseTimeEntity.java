package com.air.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass //부모 클래스를 상속받는 자식 클래스한테 매핑정보를 제공하기 위해
@Getter
@Setter
public abstract class BaseTimeEntity {
	
	@CreatedDate //엔티티가 생성되서 저장될때 시간을 자동으로 저장한다.
	@Column(updatable = false) //수정하지 못하게 막음
	private LocalDateTime regTime; //등록 날짜
	
	@LastModifiedDate //수정될때 시간을 자동으로 저장한다.
	private LocalDateTime updateTime; //수정 날짜
}

