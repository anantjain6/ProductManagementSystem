package me.anant.PMS.dao;

import org.springframework.data.repository.CrudRepository;

import me.anant.PMS.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
