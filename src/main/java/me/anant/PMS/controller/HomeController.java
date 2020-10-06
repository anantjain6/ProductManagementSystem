package me.anant.PMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import me.anant.PMS.model.Product;
import me.anant.PMS.service.ProductService;

/**
 * This Controller is responsible for functionalities related to Customer.
 */
@Controller
public class HomeController {
	@Autowired
	ProductService  productService;

	/**
	 * This api is responsible to view home page.
	 * @return index page
	 */
	@GetMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * This api is responsible to view Customer's Products.
	 * @return ModelAndView
	 */
	@GetMapping("/customer")
	public ModelAndView customerHome() {
		List<Product> pList =  productService.get();
		ModelAndView modelAndView = new ModelAndView("customer/home");
		modelAndView.addObject("pList", pList);
		return modelAndView;
	}
}
