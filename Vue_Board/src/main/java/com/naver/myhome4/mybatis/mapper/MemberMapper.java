package com.naver.myhome4.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.naver.myhome4.domain.Member;

@Mapper
public interface MemberMapper {
	
	//존재하는 아이디인지 확인 
	public Member isId(String id);
	
	//회원가입(join)
	public int insert(Member m);

	//수정 
	public int update(Member m);
	
	//삭제 
	public int delete(String id);

	//회원정보 수정 + 검색 
	public int getSearchListCount(Map<String, Object> map) ;

	public List<Member> getSearchList(Map<String, Object> map) ;
	
}
