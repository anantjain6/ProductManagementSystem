package me.anant.PMS.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import me.anant.PMS.model.Product;
import me.anant.PMS.model.ProductCategory;
import me.anant.PMS.service.ProductCategoryService;
import me.anant.PMS.service.ProductService;

/**
 * This Controller is responsible for functionalities related to Customer.
 */
@Controller
public class HomeController {
	@Autowired
	ProductService  productService;

	@Autowired
	ProductCategoryService categoryService;

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
	public ModelAndView customerHome(
		@RequestParam("categoryId") Optional<Integer> categoryId
	) {
		List<Product> pList = productService.get();
		List<ProductCategory> pcList = categoryService.get();

		ModelAndView modelAndView = new ModelAndView("customer/home");
		modelAndView.addObject("pList", pList);
		modelAndView.addObject("categoryId", categoryId);
		modelAndView.addObject("pcList", pcList);
		return modelAndView;
	}
}
