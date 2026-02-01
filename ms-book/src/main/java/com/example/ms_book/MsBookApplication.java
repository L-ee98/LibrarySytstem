package com.example.ms_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.ms_book.client")
public class MsBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBookApplication.class, args);
	}

}
