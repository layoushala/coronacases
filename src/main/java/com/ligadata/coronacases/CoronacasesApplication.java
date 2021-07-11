package com.ligadata.coronacases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication( exclude = HibernateJpaAutoConfiguration.class,
        scanBasePackages = {"com.ligadata.coronacases"})
public class CoronacasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronacasesApplication.class, args);
	}

}
