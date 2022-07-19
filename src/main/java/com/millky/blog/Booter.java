package com.millky.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
