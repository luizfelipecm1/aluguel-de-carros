package com.aluguel.sistema_de_aluguel_de_carros.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.aluguel"})
@EnableJpaRepositories("com.aluguel.sistema_de_aluguel_de_carros.repository")
@EntityScan("com.aluguel.sistema_de_aluguel_de_carros.model")
public class SistemaDeAluguelDeCarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeAluguelDeCarrosApplication.class, args);
	}

}
