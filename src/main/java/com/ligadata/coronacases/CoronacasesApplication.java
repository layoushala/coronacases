package com.ligadata.coronacases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class,
        scanBasePackages = {"com.ligadata.coronacases"})
public class CoronacasesApplication {

    public static void main(String[] args) {
       

        
        SpringApplication.run(CoronacasesApplication.class, args);
    }

}
