package com.air.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.air.entity.Member;
import com.air.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	private final MemberRepository memberRepository;
	
	//회원가입 데이터를 Db에 저장한다.
		public Member saveMember(Member member) {
			validateDuplicateMember(member); //이메일 중복체크
			Member savedMember = memberRepository.save(member); //insert
			return savedMember; //회원가입된 데이터를 리턴해준다
		}
		
		//이메일 중복체크
		private void validateDuplicateMember(Member member) {
			Member findMember = memberRepository.findByEmail(member.getEmail());
			
			if(findMember != null) {
				throw new IllegalStateException("이미 가입된 회원입니다.");
			}
	
}

		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			//사용자가 입력한 email이 DB에 있는지 쿼리문을 사용한다.
			Member member = memberRepository.findByEmail(email);
			
			if(member == null) { //사용자가 없다면
				throw new UsernameNotFoundException(email);
			}
			
			//사용자가 있다면 userDetails 객체를 만들어서 반환
			return User.builder()
					   .username(member.getEmail())
					   .password(member.getPassword())
					   .roles(member.getRole().toString())
					   .build();
		}
		
}