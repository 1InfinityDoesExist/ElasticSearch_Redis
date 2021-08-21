package com.example.elasticSearchAndRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ElasticSearchAndRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchAndRedisApplication.class, args);
	}

}
