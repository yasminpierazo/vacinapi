package com.api.vacinas;

import org.springframework.boot.SpringApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableWebMvc
public class VacinasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacinasApplication.class, args);
	}

}
