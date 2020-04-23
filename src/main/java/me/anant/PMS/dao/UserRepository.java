package me.anant.PMS.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.anant.PMS.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
