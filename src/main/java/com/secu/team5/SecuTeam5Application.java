package com.secu.team5;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan
@EnableScheduling
public class SecuTeam5Application {

	public static void main(String[] args) {
		SpringApplication.run(SecuTeam5Application.class, args);
	}

}
