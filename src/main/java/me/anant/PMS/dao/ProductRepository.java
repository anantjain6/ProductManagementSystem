package me.anant.PMS.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import me.anant.PMS.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    // find by category id
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findByCategoryId(@Param("categoryId") Long categoryId);


}
