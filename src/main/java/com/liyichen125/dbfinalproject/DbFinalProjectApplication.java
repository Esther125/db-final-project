package com.liyichen125.dbfinalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.liyichen125.dbfinalproject")
public class DbFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbFinalProjectApplication.class, args);
	}

}


