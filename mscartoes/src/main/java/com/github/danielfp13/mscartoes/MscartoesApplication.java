package com.github.danielfp13.mscartoes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@Slf4j
public class MscartoesApplication {

	public static void main(String[] args) {
		log.info("informação: {}", "teste info");
		log.error("error: {}", "teste error");
		log.warn("aviso: {}", "teste warn");
		SpringApplication.run(MscartoesApplication.class, args);
	}

}
