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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/customer/order_place")
	public ModelAndView customerHome() {
		List<Product> pList =  productService.get();
		ModelAndView modelAndView = new ModelAndView("customer/home");
		modelAndView.addObject("pList", pList);
		return modelAndView;
	}
	
	@PostMapping("/customer/order_place")
	public ModelAndView orderPlace(HttpServletRequest request, Principal principal) {
		String[] pIds = request.getParameterValues("productId");
		Set<OrderProduct> opList = new HashSet<>();
		for(String pId: pIds) {
			long pid = Long.parseLong(pId);
			Product product = productService.findById(pid).get();
			int buyqty = Integer.parseInt(request.getParameter(pId));
			opList.add(new OrderProduct(product, buyqty));
			productService.deductQty(pid, buyqty);
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
	
	@GetMapping("customer/order/cancel")
	public String orderCancel(@RequestParam("id") long id, final RedirectAttributes redirectAttributes) {
		if(orderService.cancelOrder(id)) {
			redirectAttributes.addFlashAttribute("msg", "Order cancelled successfully");
			redirectAttributes.addFlashAttribute("class", "alert-success");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Order not cancelled, since you can cencel PROCESSING or CONFIRMED order with 24 hours only.");
			redirectAttributes.addFlashAttribute("class", "alert-danger");
		}
		return "redirect:/customer/order/list";
	}
	
	@PostMapping("admin/order/status")
	public String changeStatus(@RequestParam("id") long id, @RequestParam("status") String status, final RedirectAttributes redirectAttributes) {
		orderService.changeStatus(id, status);
		redirectAttributes.addFlashAttribute("msg", "Status of order changed.");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/order/list";
	}
}
