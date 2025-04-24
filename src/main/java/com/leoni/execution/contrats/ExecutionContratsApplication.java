package com.leoni.execution.contrats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // ⬅️ Active la planification
public class ExecutionContratsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExecutionContratsApplication.class, args);
	}

}
