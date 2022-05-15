package com.sungshin.croffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@EnableJpaAuditing
@SpringBootApplication
public class CroffleExpeditionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CroffleExpeditionApplication.class, args);
	}

	//@PutMapping, @DeleteMapping 사용 위해 작성 
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
		return new HiddenHttpMethodFilter();
	}

}
