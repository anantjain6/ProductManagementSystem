package me.anant.PMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.UserRepository;
import me.anant.PMS.model.User;

@Service
public class UserService {

	UserRepository ur;

	public UserService(UserRepository ur)
	{
		this.ur=ur;
	}
	
	public User findByEmail(String email) {
		return ur.findByEmail(email);
	}
}
