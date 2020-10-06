package me.anant.PMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PmsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PmsApplication.class, args);
		System.out.println("Server Started");
	}

}
