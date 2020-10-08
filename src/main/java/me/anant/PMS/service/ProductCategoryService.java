package me.anant.PMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.ProductCategoryRepository;
import me.anant.PMS.model.ProductCategory;

@Service
public class ProductCategoryService {

	ProductCategoryRepository pcr;

	public ProductCategoryService(ProductCategoryRepository pcr)
	{
		this.pcr=pcr;
	}
	
	public void save(ProductCategory productCategory) {
		pcr.save(productCategory);
	}

	public void delete(Long id){
		pcr.deleteById(id);
	}

	public List<ProductCategory> get(){
		return (List<ProductCategory>) pcr.findAll();
	}
	public Optional<ProductCategory> findById(Long id) {
		return pcr.findById(id);
	}
}
