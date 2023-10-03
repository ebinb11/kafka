package com.bank.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.bank.dto.BankDTO;
import com.bank.bank.entity.Bank;
import com.bank.bank.service.BankService;

@Controller
@RequestMapping(value = "bank")
public class BnakController {

	@Autowired
	BankService bankService;

	@GetMapping(value = "welcome")
	ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
		List<Bank> bankList = bankService.fetchAllBankDetails();
		modelAndView.setViewName("welcome");
		modelAndView.addObject("bankList", bankList);
		return modelAndView;
	}

	@GetMapping(value = "register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		BankDTO bankDTO = new BankDTO();
		modelAndView.setViewName("register");
		modelAndView.addObject("bankDTO", bankDTO);
		return modelAndView;
	}

	@PostMapping(value = "save")
	public ModelAndView save(BankDTO bankDTO) {
		ModelAndView modelAndView = new ModelAndView();
		bankDTO = bankService.saveBank(bankDTO);
		modelAndView.addObject("bankDTO", bankDTO);
		modelAndView.setViewName("register");

		return modelAndView;
	}
}
