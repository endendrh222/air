package com.air.entity;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.air.constant.Role;
import com.air.dto.MemberFormDto;

import jakarta.persistence.*;
import lombok.*;

@Entity //엔티티 클래스로 정의
@Table(name="member") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Member extends BaseEntity{
	@Id
	@Column(name="member_id") //테이블로 생성될때 컬럼이름을 지정해준다.
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키를 자동으로 생성해주는 전략 사용
	private long id;
	
	@Column(unique = true) //중복 불가능
	private String email;
	
	private String name;
	
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING)  //enum의 이름을 db에 저장
	private Role role; 
	
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		//패스워드 암호화
		String password = passwordEncoder.encode(memberFormDto.getPassword());

		//MemberFormDto를 -> Member 엔티티 객체로 변환
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
		member.setPassword(password);
//		member.setRole(Role.ADMIN); //관리자로 가입할때
		member.setRole(Role.USER); //일반 사용자로 가입할때
		
		return member;
	}
}
