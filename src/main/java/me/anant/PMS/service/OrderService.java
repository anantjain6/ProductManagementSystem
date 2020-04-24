package me.anant.PMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.OrderRepository;
import me.anant.PMS.model.Order;

@Service
public class OrderService {

	@Autowired
	OrderRepository or;
	
	public void save(Order order) {
		or.save(order);
	}
	public void delete(Long id) {
		or.deleteById(id);
	}
	public List<Order> get(){
		return (List<Order>) or.findAll();
	}
	public Optional<Order> findById(Long id) {
		return or.findById(id);
	}
}
