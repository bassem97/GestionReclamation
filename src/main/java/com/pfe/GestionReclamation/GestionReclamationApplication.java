package com.pfe.GestionReclamation;

import com.pfe.GestionReclamation.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GestionReclamationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionReclamationApplication.class, args);
	}

}
