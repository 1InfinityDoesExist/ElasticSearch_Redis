package com.example.erk.config.kakfa.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EkPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public EkPublisher() {
		log.info("----EkPublisher bean created.");
	}

	public void publish(String topic, String msg) {
		ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, msg);
		send.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				log.info("----Successfully publish the msg : {}", result.getRecordMetadata());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.info("----Fiailed to publish the msg , Reason is {}", ex.getMessage());
			}
		});
	}
}
