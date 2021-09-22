package com.cts.capstone;

import com.cts.capstone.service.DbServiceDao;
import com.cts.capstone.service.DbServiceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(Main.class, args);

		DbServiceDao serviceDao = context.getBean(DbServiceDao.class);
		DbServiceRepository serviceRepository = context.getBean(DbServiceRepository.class);

		System.out.println("Dao");
		System.out.println(serviceDao.getProduct(1));
		System.out.println(serviceDao.getCategory(1));

		System.out.println("\nRepository");
		System.out.println(serviceRepository.getProduct(1));
		System.out.println(serviceRepository.getCategory(1));
	}
}
