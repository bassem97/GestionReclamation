package com.pfe.GestionReclamation;

import com.pfe.GestionReclamation.repository.UserRepository;
import com.pfe.GestionReclamation.service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class GestionReclamationApplication {


	public static void main(String[] args) {
		SpringApplication.run(GestionReclamationApplication.class, args);
	}



}
