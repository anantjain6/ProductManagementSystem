package me.anant.PMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "me.anant.PMS.model")
@EnableJpaRepositories(basePackages = "me.anant.PMS.dao")
@ComponentScan(basePackages = "me.anant.PMS")
public class PmsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PmsApplication.class, args);
		System.out.println("Server Started");
	}

}
