

package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 *
 *
 *
 */
@SpringBootApplication
public class BusinessApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BusinessApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BusinessApiApplication.class);
	}
}