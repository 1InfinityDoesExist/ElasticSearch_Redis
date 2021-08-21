package com.example.erk.config.kakfa.subscriber;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EkSubscriber {

	public EkSubscriber() {
		log.info("-----EkSubscriber bean created.");
	}

	@KafkaListener(topics = "currency", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void consumeText(ConsumerRecord<String, Object> message) {

		log.info("::::::messageValue {}", message.value());
		log.info("::::::messageKey {}", message.key());
	}

}
