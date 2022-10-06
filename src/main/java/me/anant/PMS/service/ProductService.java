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

	ProductRepository pr;

	public ProductService(ProductRepository pr)
	{
		this.pr=pr;
	}
	
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

		Product product = findById(id);
		int newQty = product.getProductQty() - qty;
		product.setProductQty(newQty);
		pr.save(product);
	}
	public void addQty(long id, int qty) throws ProductNotFoundException {
		Product product = findById(id);
		int newQty = product.getProductQty() + qty;
		product.setProductQty(newQty);
		pr.save(product);
	}

	public Product findById(long id) throws ProductNotFoundException {
		Optional<Product> optionalProduct = pr.findById(id);
		return optionalProduct.orElseThrow(()-> new ProductNotFoundException("Product not available"));

	}
}
