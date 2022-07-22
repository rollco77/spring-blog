package com.millky.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Booter {

	public static void main(String[] args) {
		SpringApplication.run(Booter.class, args);
	}
}

/*@SpringBootApplication
public class Booter extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Booter.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Booter.class);
	}

}*/
