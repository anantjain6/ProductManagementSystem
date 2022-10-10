package me.anant.PMS.service;

import java.util.List;
import java.util.Optional;

import me.anant.PMS.dto.CategoryDto;
import me.anant.PMS.dto.ProductDto;
import me.anant.PMS.exceptions.ProductNotFoundException;
import me.anant.PMS.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.ProductRepository;
import me.anant.PMS.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository pr;
	@Autowired
	private ProductCategoryService productCategoryService;
	
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
		if (!optionalProduct.isPresent()) {
			throw new ProductNotFoundException("Product not available");
		}
		return optionalProduct.get();
	}

	public ProductDto toDto(Product product) {
		ProductDto dto = new ProductDto();
		dto.setId(product.getProductId());
		dto.setName(product.getProductName());
		dto.setPrice(product.getProductPrice());
		dto.setQuantity(product.getProductQty());
		dto.setCategory(product.getCategory().getId());
		productCategoryService.get();
		return dto;
	}

	public CategoryDto toDto(ProductCategory category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		return categoryDto;
	}
}
