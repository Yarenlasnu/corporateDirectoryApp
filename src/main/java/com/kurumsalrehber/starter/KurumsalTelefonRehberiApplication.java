package com.kurumsalrehber.starter;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"com.KurumsalRehber.entity"})
@EnableJpaRepositories(basePackages = "com.KurumsalRehber.repository")
@ComponentScan(basePackages = "com.KurumsalRehber")
@SpringBootApplication
public class KurumsalTelefonRehberiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KurumsalTelefonRehberiApplication.class, args);
	}

}
