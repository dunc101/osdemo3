package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Osdemo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Osdemo3Application.class, args);
	}
	
	@RequestMapping("/ping")
	public String ping() {
		return "pong";
	}
}

