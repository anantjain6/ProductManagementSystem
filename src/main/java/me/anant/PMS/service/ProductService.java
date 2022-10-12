package me.anant.PMS.service;

import java.util.List;
import java.util.Optional;

import me.anant.PMS.exceptions.ProductNotFoundException;
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

	public void deductQty(long id, int qty) throws ProductNotFoundException {

		Optional<Product> optionalProduct = findById(id);
		Product product = optionalProduct.get();
		int newQty = product.getProductQty() - qty;
		product.setProductQty(newQty);
		pr.save(product);
	}
	public void addQty(long id, int qty) throws ProductNotFoundException {
		Optional<Product> optionalProduct = findById(id);
		Product product = optionalProduct.get();
		int newQty = product.getProductQty() + qty;
		product.setProductQty(newQty);
		pr.save(product);
	}

	public Optional<Product> findById(long id) throws ProductNotFoundException {
		Optional<Product> optionalProduct = pr.findById(id);
		if (!optionalProduct.isPresent()) {
			throw new ProductNotFoundException("Product not available");
		}
		return optionalProduct;
	}

	public List<Product> findByCategoryId(Long categoryId) {
		return pr.findByCategoryId(categoryId);
	}
}
