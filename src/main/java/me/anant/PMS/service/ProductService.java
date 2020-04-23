package me.anant.PMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.ProductRepository;
import me.anant.PMS.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository pr;
	
	public void save(Product product) {
		pr.save(product);
	}
	public void delete(Long id) {
		pr.deleteById(id);
	}
	public List<Product> get(){
		return (List<Product>) pr.findAll();
	}
	public Optional<Product> findById(Long id) {
		return pr.findById(id);
	}
}
