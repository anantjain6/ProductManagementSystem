package me.anant.PMS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.boot.archive.scan.spi.ClassDescriptor.Categorization;
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
		@RequestParam("categoryId") Optional<Long> categoryId
	) {
		List<Product> pList = new ArrayList<Product>();
		List<ProductCategory> pcList = categoryService.get();
		if (categoryId.isPresent()) {
			pList = productService.get().stream().filter(p -> p.getCategory().getId() == categoryId.get()).collect(Collectors.toList());
		} else {
			pList = productService.get();
		}
		ModelAndView modelAndView = new ModelAndView("customer/home");
		modelAndView.addObject("pList", pList);
		modelAndView.addObject("pcList", pcList);
		modelAndView.addObject("categoryId", categoryId);
		return modelAndView;
	}
}
