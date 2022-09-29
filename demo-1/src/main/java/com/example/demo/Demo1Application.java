package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
@SpringBootApplication
   1. 자동 설정을 담당하는 annotation으로 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성이 모두 자동으로 설정됩니다.
   
   2. SprinfBootApplications annotation이 포함하고 세 가지 annotation은 다음과 같습니다.
      (1) @SpringBootConfiguration
              - Spring Boot 애플리케이션의 @Configuration을 제공함을 나타냅니다.
              
      (2) @ComponentScan
              - @SpringBootApplication이 있는 폴더를 기준으로 하위 폴더 모두를 스캔해서 빈으로 등록합니다.
                 예) @Component, @Configuration, @Repository, @Service, @Controller, @RestController ..)
                      위 annotation을 인식하게 하기 위해서는 @SpringBootApplication이 있는 패키지부터 하위 패키지에 작성하시면 됩니다.
      
      (3) @EnableAutoConfiguration
              - @ComponentScan으로 빈이 등록된 이후, @EnableAutoConfiguration를 통해 한 번 더 스캔되는데
                  그 때 등록되는 bean들은 설정을 위해 사용되는 다양한 bean 들이 등록이 됩니다.
*
*/
@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
