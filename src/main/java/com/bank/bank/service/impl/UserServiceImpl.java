package com.bank.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.entity.User;
import com.bank.bank.entity.repository.UserRepository;
import com.bank.bank.kafka.producer.KafkaProducer;
import com.bank.bank.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	KafkaProducer kafkaProducer;

	@Override
	public User save(User user) {
		user = userRepository.save(user);
		kafkaProducer.publishKafkaMessageUser(user.getName());
		return user;
	}

	@Override
	public List<User> fetchAllData() {
		List<User> users= userRepository.findAll();
		return users;
	}

}
