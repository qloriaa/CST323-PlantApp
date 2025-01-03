package com.cst323activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.cst323activity" })
public class PlantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantsApplication.class, args);
	}

}
