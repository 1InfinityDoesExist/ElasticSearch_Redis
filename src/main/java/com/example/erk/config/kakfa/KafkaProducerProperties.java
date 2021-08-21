package com.example.erk.config.kakfa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConditionalOnProperty(prefix = "kafka", name = "enable", havingValue = "true")
public class KafkaProducerProperties {

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstarpServers;
	@Value("${spring.kafka.producer.key-serializer}")
	private String keySerializer;
	@Value("${spring.kafka.producer.value-serializer}")
	private String valueSerialzier;
	@Value("${spring.kafka.producer.acks}")
	private String acks;
	@Value("${spring.kafka.producer.retries}")
	private String retries;
	@Value("${spring.kafka.producer.batch-size}")
	private String batchSize;
	@Value("${spring.kafka.producer.buffer-memory}")
	private String bufferMemory;

}
