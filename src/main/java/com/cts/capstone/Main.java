package com.cts.capstone;

import com.cts.capstone.service.DbService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(Main.class, args);

		DbService service = context.getBean(DbService.class);
		System.out.println(service.getProduct(1));
	}
}
