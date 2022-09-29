package com.naver.myhome4.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.myhome4.domain.Member;
import com.naver.myhome4.service.MemberService;

/*
	@Component를 이용해서 스프링 컨테이너가 해당 클래스 객체를 생성하도록 설정할 수 있지만
	모든 클래스에 @Component를 할당하면 어떤 클래스가 어떤 역할을 수행하는지 파악하기
	어렵습니다. 스프링 프레임워크에서는 이런 클래스들을 분류하기 위해서
	@Component를 상속하여 다음과 같은 세 개의 annotation을 제공합니다.
 
	1. @Controller - 사용자의 요청을 제어하는 Controller 클래스
	2. @Repository - 데이터 베이스 연동을 처리하는 DAO 클래스
	3. @Service - 비즈니스 로직을 처리하는 Service 클래스
 
 */

@RestController
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService memberservice;
	
	@Autowired
	public MemberController(MemberService memberservice) {
		this.memberservice = memberservice;
	}
	
	
	
	//로그아웃
	@RequestMapping(value = "/members/logout", method = RequestMethod.POST)
	public String loginout(HttpSession session) {
		session.invalidate();
		return "logout success";
	}
	
	
	//로그인 처리
   @PostMapping(value = "/members")
   public int loginProcess(
      @RequestBody Member member,
      HttpSession session) {
      
      String id= member.getId();
      String password=member.getPassword();
      int result = memberservice.isId(id, password);
      logger.info("결과 : " + result);
      if (result == 1) {
         // 로그인 성공
         session.setAttribute("id", id);
      }
      return result;
   }
	
	// 회원가입품에서 아이디 검사
	@GetMapping(value = "/members/idcheck")
	public int idcheck(String id)	{
		logger.info("idcheck");
		return memberservice.isId(id);
	}
	
	
	// 회원가입처리
	@PostMapping(value="/members/new") 
	public int joinProcess(@RequestBody Member member)	{
		return memberservice.insert(member);
	}
	
	// 회원의 개인 정보
   @RequestMapping(value = "/members/{id}", method = RequestMethod.GET)
   public Member member_info(@PathVariable String id) {
      logger.info("id=" + id);
      return memberservice.member_info(id);
   }
   
   // 수정 처리
   @PutMapping(value = "/members")
   public int updateProcess(@RequestBody Member member) {
      return memberservice.update(member);
   }
   
   // 삭제
   @DeleteMapping(value = "/members/{id}")
   public int member_delete(@PathVariable String id) {
      return memberservice.delete(id);
   }
   
   @GetMapping(value = "/members")
   public Map<String,Object> memberList (
		   @RequestParam(value = "page", defaultValue = "1", required = false) int page,
		   @RequestParam(value = "limit", defaultValue = "3", required = false) int limit,
		   @RequestParam(value = "search_field", defaultValue = "") String index,
		   @RequestParam(value = "search_word", defaultValue = "") String search_word) {
	   
	   List<Member> list = null;
	   int listcount = 0;
	   
	   listcount = memberservice.getSearchListCount(index, search_word); // 총 리스트 수를 받아옴
	   
	   //총 페이지 수
	   int maxpage = (listcount + limit - 1) / limit;
	   
	   if(page>maxpage) {
		   page=maxpage;
	   }
	   
	   list = memberservice.getSearchList(index, search_word, page, limit);
	   
	   // 현재 페이지에 보여줄 시작 페이지수(1, 11, 21 등...)
	   int startpage = ((page - 1) / 10) * 10 + 1;
	   
	   // 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
	   int endpage = startpage + 10 - 1;
	   
	   if (endpage > maxpage)
		   endpage = maxpage;
		   
	   Map<String, Object> map= new HashMap<String,Object>();
	   map.put("page", page);
	   map.put("maxpage", maxpage);
	   map.put("startpage", startpage);
	   map.put("endpage", endpage);
	   map.put("listcount", listcount);
	   map.put("memberlist", list);
	   map.put("search_field", index);
	   map.put("search_word", search_word);
	   return map;
   }
}
