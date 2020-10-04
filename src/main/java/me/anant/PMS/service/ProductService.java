package me.anant.PMS.service;

import java.util.Collections;
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
		List<Product> products = (List<Product>) pr.findAll();
		Collections.sort(products);
		return products;
	}
	public Optional<Product> findById(Long id) {
		return pr.findById(id);
	}
	public void deductQty(long id, int qty) {
		Product product = pr.findById(id).get();
		int newQty = product.getProductQty() - qty;
		product.setProductQty(newQty);
		pr.save(product);
	}
	public void addQty(long id, int qty) {
		Product product = pr.findById(id).get();
		int newQty = product.getProductQty() + qty;
		product.setProductQty(newQty);
		pr.save(product);
	}
}
