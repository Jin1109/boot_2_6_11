package com.example.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController4 { //main()있는 클래스와 같은 패키지도 아니고 하위 패키지가 아니라서 스캔 대상이 아닙니다.

	@GetMapping("/index2")
	public String hello() {
		return "저는 스캔 대상이 아닙니다..";
	}
}
