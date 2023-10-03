package com.bank.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.dto.BankDTO;
import com.bank.bank.dto.KafkaDTO;
import com.bank.bank.entity.Bank;
import com.bank.bank.entity.repository.BankRepository;
import com.bank.bank.kafka.producer.KafkaProducer;
import com.bank.bank.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	KafkaProducer kafkaProducer;

	@Override
	public BankDTO saveBank(BankDTO bankGet) {
		Bank bank = new Bank();
		try {
			bank.setAddress(bankGet.getAddress());
			bank.setName(bankGet.getName());
			bank = bankRepository.save(bank);
			
			KafkaDTO kafkaDTO = new KafkaDTO();
			kafkaDTO.setName(bank.getName());
			kafkaProducer.publishKafkaMessageBank(kafkaDTO);
			return bankGet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bank> fetchAllBankDetails() {
		List<Bank> banks = bankRepository.findAll();
		return banks;
	}

}
