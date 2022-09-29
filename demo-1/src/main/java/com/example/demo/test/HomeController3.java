package com.example.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController3 { //main()있는 클래스와 같은 패키지도 아니고 하위 패키지가 아니라서 스캔 대상이 아닙니다.

	@GetMapping("/hello2")
	public String hello() {
		return "Hello Spring Boot~";
	}
}
