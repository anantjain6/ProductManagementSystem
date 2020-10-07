package me.anant.PMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.UserRepository;
import me.anant.PMS.model.Password;
import me.anant.PMS.model.User;

@Service
public class UserService {
	@Autowired
	UserRepository ur;
	
	public User findByEmail(String email) {
		return ur.findByEmail(email);
	}
	
	public boolean updateUserPassword(String userName, Password password)
    {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	User user = ur.findByEmail(userName);
        if(user == null) {
        	throw new UsernameNotFoundException("User not found");
        }
        
        if(validatePassword(user, password)) {
        	user.setPassword(encoder.encode(password.getNewPassword()));
        	ur.save(user);
        	return true;
        }
        return false;
    }
    
    private boolean validatePassword(User user, Password password)
    {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	if(!encoder.matches(password.getOldPassword(), user.getPassword())) {
    		return false;
    	}
    	
    	if(!password.getNewPassword().equals(password.getConfirmPassword())) {
    		return  false;
    	}
    	
    	
    	return true;
    }
}
