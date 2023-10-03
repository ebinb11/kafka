package com.bank.bank.service;

import java.util.List;

import com.bank.bank.dto.BankDTO;
import com.bank.bank.entity.Bank;

public interface BankService {

	BankDTO saveBank(BankDTO bankDTO);

	List<Bank> fetchAllBankDetails();

}
