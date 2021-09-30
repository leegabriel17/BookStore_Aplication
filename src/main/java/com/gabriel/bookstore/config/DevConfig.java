package com.gabriel.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabriel.bookstore.service.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean //esse metodo verifica se esta recriando o base de dados, pois verifica se ja foi criado o base de dados antes 
	public boolean instanciaBasedeDados() {
		if(strategy.equals("create")) {
			this.dbService.instanciaBasedeDados();
		}
		return false;
	}
	
	
}
