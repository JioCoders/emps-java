package com.springboot.empc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class EmpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpcApplication.class, args);
		log.info("=======Application has been started. OK=======");
	}

}
