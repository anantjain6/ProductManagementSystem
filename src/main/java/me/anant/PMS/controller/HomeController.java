package me.anant.PMS.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import me.anant.PMS.model.Product;
import me.anant.PMS.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService  productService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/customer")
	public ModelAndView customerHome() {
		List<Product> pList =  productService.getAvailableProducts();
		ModelAndView modelAndView = new ModelAndView("customer/home");
		modelAndView.addObject("pList", pList);
		return modelAndView;
	}
}
