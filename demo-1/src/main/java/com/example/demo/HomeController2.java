package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
spring-boot-starter-web 에 포함된 tomcat 은 JSP 엔진을 포함하고 있지 않습니다.
jsp 파일은 Springboot 의 templates 폴더안에서 작동하지 않습니다.
그래서 jsp를 적용하기 위해서는 아래와 같은 의존성을 추가해야 합니다.
======================================================
pom.xml
<dependency>
   <groupId>javax.servlet</groupId>
   <artifactId>jstl</artifactId>
</dependency>
<dependency>
   <groupId>org.apache.tomcat.embed</groupId>
   <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
 */
@Controller
public class HomeController2 {

	@GetMapping("/index")
	public String hello() {
		return "test";
	}
}
