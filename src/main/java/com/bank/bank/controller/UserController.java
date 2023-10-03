package com.bank.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.bank.entity.Bank;
import com.bank.bank.entity.User;
import com.bank.bank.service.BankService;
import com.bank.bank.service.UserService;


@Controller
@RequestMapping(value = "user")
public class UserController {

	
	@Autowired
	UserService userService;
	@Autowired
	BankService bakBankService;
	
	@GetMapping(value = "register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		List<User> userList = userService.fetchAllData();
		List<Bank> bankList = bakBankService.fetchAllBankDetails();
		modelAndView.setViewName("user/register");
		modelAndView.addObject("bankList", bankList);
		modelAndView.addObject("user", user);
		modelAndView.addObject("userList", userList);
		return modelAndView;
	}

	@PostMapping(value = "save")
	public ModelAndView save(User user) {
		ModelAndView modelAndView = new ModelAndView();
		List<User> userList = userService.fetchAllData();

		user = userService.save(user);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user/register");
		modelAndView.addObject("userList", userList);


		return modelAndView;
	}
}
