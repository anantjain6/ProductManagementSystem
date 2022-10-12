package me.anant.PMS.service;

import java.util.List;
import java.util.Optional;

import me.anant.PMS.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.ProductRepository;
import me.anant.PMS.model.Product;

@Service
public class ProductService {

	final ProductRepository pr;

	public ProductService(ProductRepository pr) {
		this.pr = pr;
	}

	public Product save(Product product) {
		return pr.save(product);
	}
	public void delete(Long id) {
		pr.deleteById(id);
	}
	public List<Product> get(){
		return (List<Product>) pr.findAll();
	}

	public void updateProduct(Product newProduct) throws ProductNotFoundException {
		findById(newProduct.getProductId()); // ensure that the product exists.

		save(newProduct);
	}

	public Product findById(long id) throws ProductNotFoundException {
		Optional<Product> optionalProduct = pr.findById(id);
		if (!optionalProduct.isPresent()) {
			throw new ProductNotFoundException("Product not available");
		}
		return optionalProduct.get();
	}

	public List<Product> findByCategoryId(Long categoryId) {
		return pr.findByCategoryId(categoryId);
	}
}
