package com.bank.bank.service;

import java.util.List;

import com.bank.bank.entity.User;

public interface UserService {

	User save(User user);

	List<User> fetchAllData();

}
