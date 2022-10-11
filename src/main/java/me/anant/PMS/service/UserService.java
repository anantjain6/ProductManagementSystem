package me.anant.PMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.UserRepository;
import me.anant.PMS.model.User;

@Service
public class UserService {
	@Autowired
	UserRepository ur;
	
	public User findByEmail(String email) {
		return ur.findByEmail(email);
	}

	public List<User> get(){
		return ur.findAll();
	}
}
