package com.nonbinsys.greeners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class GreenersApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenersApplication.class, args);
	}

}
