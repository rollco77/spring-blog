package com.millky.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 20220804 spring 테스트 : @SpringBootApplication 이 적용된 class 하위 패키지 부터 spring Bean 의 생성이 가능하다.
 * Booter 의 package 가 com.millky.blog 이고 의존성을 주입하려는 bean 의 경로가 com.milky.common 이면 spring bean으로 의존성 주입할 수 없다.
 */
/*@SpringBootApplication
public class Booter {

	public static void main(String[] args) {
		SpringApplication.run(Booter.class, args);
	}
}*/


/**
 * WAS에 배포하는 경우 아래 주석을 해제하고 main method는 주석처리
 */
@SpringBootApplication
@EnableAsync
public class Booter extends SpringBootServletInitializer {


	public Booter() {
		super();
		//setRegisterErrorPageFilter(false); // <- this one
	}

	public static void main(String[] args) {

		SpringApplication.run(Booter.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Booter.class);
	}
}