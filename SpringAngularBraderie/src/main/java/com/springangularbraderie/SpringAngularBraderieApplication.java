package com.springangularbraderie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude = { SecurityAutoConfiguration.class} permet de "supprimer" la page login de spring security
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringAngularBraderieApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularBraderieApplication.class, args);
	}

}
