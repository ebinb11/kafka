package com.bank.bank.kafka.consumer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bank.bank.util.KafkaConstants;

import lombok.extern.slf4j.Slf4j;

@Component
@Transactional
@ConditionalOnProperty(prefix = "service.kafka", name = "enabled", matchIfMissing = false)
@Slf4j
public class KafkaConsumer {

	@KafkaListener(topics = KafkaConstants.TOPIC_ESAF_USER, containerFactory = "kafkaListenerContainerFactoryUser")
	public void listenKafkaMessageBillBaseApprovedSalary(String message, Acknowledgment ack) {
		log.info("USER ID: {} Listening to USER Event on topic '{}'", message);
		ack.acknowledge();
	}
}
