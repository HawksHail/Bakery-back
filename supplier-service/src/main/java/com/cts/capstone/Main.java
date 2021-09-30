package com.cts.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaClient
@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(Main.class, args);
	}

}
