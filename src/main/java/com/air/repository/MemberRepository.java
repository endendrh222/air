package com.air.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.air.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	//select * from member where email = ?
		Member findByEmail(String email);
}
