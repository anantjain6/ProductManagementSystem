package me.anant.PMS.controller;

import java.awt.Dialog.ModalExclusionType;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import me.anant.PMS.model.Order;
import me.anant.PMS.model.OrderProduct;
import me.anant.PMS.model.Product;
import me.anant.PMS.model.User;
import me.anant.PMS.service.OrderService;
import me.anant.PMS.service.ProductService;
import me.anant.PMS.service.UserService;

@Controller
public class OrderController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/customer/order_place")
	public ModelAndView orderPlace(HttpServletRequest request, Principal principal) {
		String[] pIds = request.getParameterValues("productId");
		Set<OrderProduct> opList = new HashSet<>();
		for(String pId: pIds) {
			Product product = productService.findById(Long.parseLong(pId)).get();
			int buyqty = Integer.parseInt(request.getParameter(pId));
			opList.add(new OrderProduct(product, buyqty));
		}
		User user = userService.findByEmail(principal.getName());
		orderService.save(new Order(user, "PROCESSING", opList));
		ModelAndView modelAndView = new ModelAndView("customer/order_place");
		modelAndView.addObject("opList", opList);
		return modelAndView;
	}
	
	@GetMapping("customer/order/list")
	public ModelAndView viewMyOrder(Principal principal) {
		User user = userService.findByEmail(principal.getName());
		ModelAndView modelAndView = new ModelAndView("customer/order/list");
		modelAndView.addObject("orderList", user.getOrders());
		return modelAndView;
	}
	
	@GetMapping("admin/order/list")
	public ModelAndView viewAllOrder() {
		List<Order> oList = orderService.get();
		ModelAndView modelAndView = new ModelAndView("admin/order/list");
		modelAndView.addObject("oList", oList);
		return modelAndView;
	}
	
	@GetMapping("admin/order/detail")
	public ModelAndView orderDetailAdmin(@RequestParam("id") long id) {
		ModelAndView modelAndView = new ModelAndView("admin/order/detail");
		modelAndView.addObject("order", orderService.findById(id).get());
		return modelAndView;
	}
	
	@GetMapping("customer/order/detail")
	public ModelAndView orderDetailCustomer(@RequestParam("id") long id) {
		ModelAndView modelAndView = new ModelAndView("customer/order/detail");
		modelAndView.addObject("order", orderService.findById(id).get());
		return modelAndView;
	}
}
