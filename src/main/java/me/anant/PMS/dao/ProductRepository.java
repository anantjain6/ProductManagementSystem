package me.anant.PMS.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import me.anant.PMS.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findAllOrderByProductNameAsc();
}
