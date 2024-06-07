package com.hhong.Volunteer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//need to get this to automatically scan all packages
//@ComponentScan(basePackages = "controllers")
//@ComponentScan(basePackages = "services")
//@ComponentScan(basePackages = "repositories")
@PropertySource("classpath:application.yml")
public class VolunteerApplication {
	public static void main(String[] args) {
		SpringApplication.run(VolunteerApplication.class, args);
	}
}
