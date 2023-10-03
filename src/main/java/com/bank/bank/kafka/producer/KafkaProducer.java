package com.bank.bank.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.bank.bank.dto.KafkaDTO;
import com.bank.bank.util.KafkaConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplateForUser;
	
	@Autowired
	KafkaTemplate<String, KafkaDTO> kafkaTemplateForBank;

	public void publishKafkaMessageUser(String message) {
		String userIdentifier = message.toString();

		log.info("USERID: {} Publishing USER Event to topic '{}'", userIdentifier, KafkaConstants.TOPIC_ESAF_USER);

		try {
			ListenableFuture<SendResult<String, String>> result = kafkaTemplateForUser
					.send(KafkaConstants.TOPIC_ESAF_USER, userIdentifier, message);
			result.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
				@Override
				public void onSuccess(@Nullable SendResult<String, String> arg0) {
					log.info("USERID: {} Publishing USER Event to topic '{}'. Status: SUCCESS.",
							userIdentifier, KafkaConstants.TOPIC_ESAF_USER);
				}

				@Override
				public void onFailure(Throwable ex) {
					log.error(
							"USERID: {} Publishing USER Event to topic '{}'. Status: FAILURE. Error Message: {}",
							userIdentifier, KafkaConstants.TOPIC_ESAF_USER, ex);
				}
			});
		} catch (Exception e) {
			log.error("USERID: {} Publishing USER Event to topic '{}'. Status: EXCEPTION. Error Message: {}",
					userIdentifier, KafkaConstants.TOPIC_ESAF_USER, e);
		}
	}
	
	public void publishKafkaMessageBank(KafkaDTO message) {
		String userIdentifier = message.getName();

		log.info("BANKID: {} Publishing BANK Event to topic '{}'", userIdentifier, KafkaConstants.TOPIC_BANK);

		try {
			ListenableFuture<SendResult<String, KafkaDTO>> result = kafkaTemplateForBank
					.send(KafkaConstants.TOPIC_BANK, userIdentifier, message);
			result.addCallback(new ListenableFutureCallback<SendResult<String, KafkaDTO>>() {
				@Override
				public void onSuccess(@Nullable SendResult<String, KafkaDTO> arg0) {
					log.info("BANKID: {} Publishing BANK Event to topic '{}'. Status: SUCCESS.",
							userIdentifier, KafkaConstants.TOPIC_BANK);
				}

				@Override
				public void onFailure(Throwable ex) {
					log.error(
							"BANKID: {} Publishing BANK Event to topic '{}'. Status: FAILURE. Error Message: {}",
							userIdentifier, KafkaConstants.TOPIC_BANK, ex);
				}
			});
		} catch (Exception e) {
			log.error("USERID: {} Publishing BANK Event to topic '{}'. Status: EXCEPTION. Error Message: {}",
					userIdentifier, KafkaConstants.TOPIC_BANK, e);
		}
	}
}
