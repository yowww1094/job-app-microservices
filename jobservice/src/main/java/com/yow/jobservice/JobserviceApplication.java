package com.yow.jobservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JobserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobserviceApplication.class, args);
	}

}
