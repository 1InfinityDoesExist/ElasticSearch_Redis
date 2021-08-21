package com.example.erk.config.kakfa.publisher;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.example.erk.config.kakfa.KafkaProducerProperties;

@EnableKafka
@Configuration
@ConditionalOnProperty(prefix = "kafka", name = "enable", havingValue = "true")
public class EkPublisherConfig {

	@Autowired
	private KafkaProducerProperties kafkaProperties;

	@Bean
	public Map<String, Object> publisherConfig() {

		Map<String, Object> properties = new LinkedHashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstarpServers());
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getKeySerializer());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getValueSerialzier());
		properties.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getAcks());
		properties.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getRetries());
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProperties.getBatchSize());
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProperties.getBufferMemory());
		return properties;
	}

	@Bean
	public ProducerFactory<String, Object> kafkaProducerFactory() {
		return new DefaultKafkaProducerFactory<String, Object>(publisherConfig());
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<String, Object>(kafkaProducerFactory());
	}

	@Bean("ekPublisher")
	public EkPublisher ekPublisher() {
		return new EkPublisher();
	}

}
