package com.humantalent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan({"com.humantalent.adapters.controllers", "com.humantalent.application", "com.humantalent.domain.mapper"})
//@EntityScan("com.humantalent.domain.model")
//@EnableJpaRepositories("com.humantalent.adapters.repositories")

public class HumanTalentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanTalentApplication.class, args);
	}

}
