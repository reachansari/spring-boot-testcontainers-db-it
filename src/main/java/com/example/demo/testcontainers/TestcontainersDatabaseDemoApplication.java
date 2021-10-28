package com.example.demo.testcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
public class TestcontainersDatabaseDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcontainersDatabaseDemoApplication.class, args);
	}

}
