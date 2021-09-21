package com.cts.capstone;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableTransactionManagement
@ComponentScan("com.cts")
public class AppConfiguration {
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(mysqlDataSource());
//	}

//	@Bean
//	public DataSource mysqlDataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		InputStream input = classLoader.getResourceAsStream("connection.properties");
//		Properties properties = new Properties();
//		try {
//			properties.load(input);
//			dataSource.setDriverClassName(properties.getProperty("driver"));
//			dataSource.setUrl(properties.getProperty("url"));
//			dataSource.setUsername(properties.getProperty("username"));
//			dataSource.setPassword(properties.getProperty("password"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return dataSource;
//	}

//	@Bean
//	public PlatformTransactionManager txManager() {
//		return new DataSourceTransactionManager(mysqlDataSource());
//	}

}
