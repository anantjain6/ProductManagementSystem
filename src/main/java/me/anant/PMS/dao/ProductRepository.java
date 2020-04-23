package me.anant.PMS.dao;

import org.springframework.data.repository.CrudRepository;

import me.anant.PMS.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
