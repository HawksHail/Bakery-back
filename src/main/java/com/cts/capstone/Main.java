package com.cts.capstone;

import com.cts.capstone.service.DbService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguation.class);
		DbService service = context.getBean(DbService.class);
		System.out.println(service.getProduct(1));
	}
}
