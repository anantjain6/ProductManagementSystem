package me.anant.PMS.dao;

import org.springframework.data.repository.CrudRepository;

import me.anant.PMS.model.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

}
