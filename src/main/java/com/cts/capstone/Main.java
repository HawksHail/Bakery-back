package com.cts.capstone;

import com.cts.capstone.service.DbService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppConfiguration.class);

		DbService service = context.getBean(DbService.class);
		System.out.println(service.getProduct(1));
		System.out.println(service.getProduct(2));
	}
}
