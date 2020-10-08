package me.anant.PMS.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import me.anant.PMS.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import me.anant.PMS.dao.OrderRepository;
import me.anant.PMS.model.Order;
import me.anant.PMS.model.OrderProduct;
import me.anant.PMS.model.Product;

import javax.servlet.http.HttpServletRequest;

@Service
public class OrderService {

	OrderRepository or;
	ProductService ps;

	public OrderService(OrderRepository orderRepository,ProductService productService)
	{
		this.or=orderRepository;
		this.ps=productService;
	}
	
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
		Order order = this.findById(id);
		LocalDateTime ldt1 = order.getCreateDateTime();
		LocalDateTime ldt2 = LocalDateTime.now();
		if(Duration.between(ldt1, ldt2).toHours() < 24) {
			if(!order.getStatus().equals("CANCEL") && !order.getStatus().equals("REJECT")) {
				for(OrderProduct op: order.getOrderProduct()) {
					ps.addQty(op.getProduct().getProductId(), op.getBuyqty());
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
		Order order = this.findById(id);
		if(status.equals("REJECT")) {
			for(OrderProduct op: order.getOrderProduct()) {
				ps.addQty(op.getProduct().getProductId(), op.getBuyqty());
			}
		}
		order.setStatus(status);
		or.save(order);
	}

	public Order findById(Long id) throws ProductNotFoundException {
		Optional<Order> optionalOrder = or.findById(id);
		return optionalOrder.orElseThrow(()->new ProductNotFoundException("Order not found within the system"));

	}

	public Set<OrderProduct> createOrderedProduct(String[] pIds, HttpServletRequest request)
			throws ProductNotFoundException {
		Set<OrderProduct> opList = new HashSet<>();
		for(String pId: pIds) {
			long pid = Long.parseLong(pId);
			Product product = this.ps.findById(pid);
			int buyqty = Integer.parseInt(request.getParameter(pId));
			opList.add(new OrderProduct(product, buyqty));
			this.ps.deductQty(pid, buyqty);
		}
		return opList;
	}
}
