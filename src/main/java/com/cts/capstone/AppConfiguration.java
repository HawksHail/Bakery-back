package com.cts.capstone;

import com.cts.capstone.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.cts")
public class AppConfiguration {

	@Bean
	public Gson gson() {
		return new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();
	}

}
