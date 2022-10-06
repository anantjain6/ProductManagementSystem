package me.anant.PMS.controller;

import java.util.List;

import me.anant.PMS.Helper.ModelAndViewProviderHelper;
import me.anant.PMS.RequestMappingConstants.ViewConstants;
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

	ProductService  productService;
	ModelAndViewProviderHelper modelAndViewProviderHelper;

	public HomeController(ProductService productService,ModelAndViewProviderHelper modelAndViewProviderHelper)
	{
		this.productService=productService;
		this.modelAndViewProviderHelper=modelAndViewProviderHelper;
	}

	/**
	 * This api is responsible to view home page.
	 * @return index page
	 */
	@GetMapping("/")
	public String index() {
		return ViewConstants.HOME_PAGE;
	}

	/**
	 * This api is responsible to view Customer's Products.
	 * @return ModelAndView
	 */
	@GetMapping("/customer")
	public ModelAndView customerHome() {
		List<Product> pList =  productService.get();
		return this.modelAndViewProviderHelper.generateModelAndView(ViewConstants.CUSTOMER_HOME_PAGE,"pList",pList);
	}
}
