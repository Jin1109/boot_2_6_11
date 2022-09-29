package com.hta.vue;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hta.vue.domain.DTO;
import com.hta.vue.service.VueService;



/**
 * CORS(Cross-origin resource sharing)
  1. 웹 페이지의 제한된 자원을 외부 도메인에서 접근을 허용해주는 메커니즘입니다.
  
  2. origin이란 URL 구조에서 protocol, host, port를 합친 것을 의미합니다.
  
  3. 예) http://localhost:8088/vue/login
        프로토콜://host:포트번호/경로
        
        http://localhost:8081/경로 에서 http://localhost:8088/경로를 호출하면
        origin이 다르기 때문에
        브라우저에서는 동일 출처 정책(Same Origin Policy)에 의해 오류가 발생합니다.
        
  4. 위 문제를 해결하기 위한 설정 방법
      (1) 각 컨트롤러에서 설정
       @CrossOrigin(origins = "http://localhost:8081")
       
      (2) 환경설정 파일에 설정
         예) WebMvcConfig.java에서
         
       @Override
         public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
            .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            .allowedOrigins("http://localhost:8081");
         }
 */
//@CrossOrigin(origins = "http://loacalhost:8081")

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private VueService service;
	
	@Autowired
	public HomeController(VueService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getList(
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int limit){
		
		int listcount = service.getListCount();
		
		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;
		
		//만약 maxpage가 2이고 page도 2인 경우
		//2페이지의 목록의 수가 한 개인 상태에서 남은 항목 한개를 삭제한 경우
		//maxpage=1이 되고 page=2가 됩니다. 이런 경우 page는 maxpage로 수정합니다.
		if(page>maxpage) {
			page=maxpage;
		}
		
		// 현재 페이지에 보여줄 시작 헤이지 수(1, 11, 21 등...)
		int startpage = ((page - 1) / 10) * 10 + 1;
		
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 - 1;
		
		if(endpage > maxpage)
			endpage = maxpage;
		
		List<DTO> list = service.getList(page, limit);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("list", list);
		map.put("listcount", listcount);
		map.put("currentpage", page);
		return map;
	}
	
	//@RequestBody는 클라이너트가 전송하는 HTTP Body 내용을 Java Object로 변환시켜주는 역할을 합니다.
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public Integer insert(@RequestBody DTO dto) {
		return service.insert(dto);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.PATCH)
	@ResponseBody
	public void update(@RequestBody DTO dto) {
		service.update(dto);
	}
	
	/*
	    /user1/1, /users/2... 처럼 주소 일부분의 값이 바뀌는 경우 {템플릿변수}로 표시하고
	     이 값을 사용하기 위해 @PathVariable 이용합니다.
	*/
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int id) {
	   service.delete(id);
	}
	
	//ex11_nav/App1_view.vue 테스트
	@RequestMapping(value = "/getSession_ex", method = RequestMethod.GET)
	@ResponseBody
	public String getSession_ex(HttpSession session) {
		session.setAttribute("id", "admin2");
		//session.setAttribute("id", "admin2");
		logger.info("id=" + (String) session.getAttribute("id"));
		return (String) session.getAttribute("id");
	}
	
	
	//ex12_router_link/App1_ex.vue 테스트
	@RequestMapping(value = "/getSession_ex1", method = RequestMethod.GET)
	@ResponseBody
	public String getSession_ex1(HttpSession session) {
	   logger.info("id2=" + (String) session.getAttribute("id2"));
	   return (String) session.getAttribute("id2");
	}
}
