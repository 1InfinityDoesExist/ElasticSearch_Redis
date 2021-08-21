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
public class KafkaSubscriberProperties {
	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String bootstrapServers;
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String autoOffsetReset;
	@Value("${spring.kafka.consumer.key-deserializer}")
	private String keyDeserializer;
	@Value("${spring.kafka.consumer.value-deserializer}")
	private String valueDeserializer;
	@Value("${spring.kafka.consumer.enable-auto-commit}")
	private String enableAutoCommit;
	@Value("${spring.kafka.consumer.auto-commit-interval}")
	private String autoCommitInterval;
	@Value("${spring.kafka.consumer.session.timeout}")
	private String sessionTimeout;
	@Value("${spring.kafka.consumer.factory.concurrency:4}")
	private int concurrency;
	@Value("${spring.kafka.consumer.timeout:10000}")
	private int timeout;
	@Value("${spring.kafka.consumer.pool.size:10}")
	private int poolSize;
}
