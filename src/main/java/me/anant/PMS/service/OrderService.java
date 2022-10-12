package me.anant.PMS.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import me.anant.PMS.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.OrderRepository;
import me.anant.PMS.model.Order;
import me.anant.PMS.model.OrderProduct;
import me.anant.PMS.model.Product;

@Service
public class OrderService {

	@Autowired
	OrderRepository or;
	
	@Autowired
	ProductService ps;
	
	public void save(Order order) {
		or.save(order);
	}
	public void delete(Long id) {
		or.deleteById(id);
	}
	public List<Order> get(){
		return (List<Order>) or.findAll();
	}

	public boolean cancelOrder(long id) throws ProductNotFoundException {
		Order order = this.findById(id).get();
		LocalDateTime ldt1 = order.getCreateDateTime();
		LocalDateTime ldt2 = LocalDateTime.now();
		if(Duration.between(ldt1, ldt2).toHours() < 24) {
			if(!order.getStatus().equals("CANCEL") && !order.getStatus().equals("REJECT")) {
				for(OrderProduct op: order.getOrderProduct()) {
					Product product = op.getProduct();
					product.setProductQty(product.getProductQty() + op.getBuyqty());
					ps.updateProduct(product);
				}
				order.setStatus("CANCEL");
				or.save(order);
				return true;
			} else {
				return false;
			}
		} else {
			 return false;
		}
	}
	public void changeStatus(long id, String status) throws ProductNotFoundException {
		Order order = this.findById(id).get();
		if(status.equals("REJECT")) {
			for(OrderProduct op: order.getOrderProduct()) {
				Product product = op.getProduct();
				product.setProductQty(product.getProductQty() + op.getBuyqty());
				ps.updateProduct(product);
			}
		}
		order.setStatus(status);
		or.save(order);
	}

	public Optional<Order> findById(Long id) throws ProductNotFoundException {
		Optional<Order> optionalOrder = or.findById(id);
		if (!optionalOrder.isPresent()) {
			throw new ProductNotFoundException("Order not found within the system");
		}
		return optionalOrder;
	}
}
