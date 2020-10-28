package com.example.elasticSearchAndRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ElasticSearchAndRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchAndRedisApplication.class, args);
    }

}
