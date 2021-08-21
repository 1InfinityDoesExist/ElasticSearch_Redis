package com.example.erk.config.kakfa.subscriber;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.erk.config.kakfa.KafkaSubscriberProperties;

@Configuration
@EnableKafka
@ConditionalOnProperty(prefix = "kafka", name = "enable", havingValue = "true")
public class EkSubscriberConfig {

	@Autowired
	private KafkaSubscriberProperties kafkaProperties;

	@Bean
	public Map<String, Object> subscriberConfig() {
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getAutoOffsetReset());
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getKeyDeserializer());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getValueDeserializer());
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaProperties.getEnableAutoCommit());
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaProperties.getAutoCommitInterval());
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaProperties.getSessionTimeout());
		return properties;
	}

	@Bean
	public ConsumerFactory<String, String> kafkaConsumerFactor() {
		return new DefaultKafkaConsumerFactory<String, String>(subscriberConfig());

	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(kafkaConsumerFactor());
		factory.setConcurrency(kafkaProperties.getConcurrency());
		factory.getContainerProperties().setPollTimeout(kafkaProperties.getTimeout());
		factory.getContainerProperties().setConsumerTaskExecutor(executor());
		return factory;
	}

	@Bean
	public AsyncListenableTaskExecutor executor() {
		ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
		tpte.setCorePoolSize(kafkaProperties.getPoolSize());
		return tpte;
	}

	@Bean("ekSubscriber")
	public EkSubscriber ekSubscriber() {
		return new EkSubscriber();
	}
}
