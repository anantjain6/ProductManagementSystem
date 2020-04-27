package me.anant.PMS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import me.anant.PMS.model.Product;
import me.anant.PMS.service.ProductCategoryService;

@Controller
public class ProductCategoryController {
	@Autowired
	ProductCategoryService categoryService;
	
	@GetMapping("admin/category/list")
	public ModelAndView categoryList() {
		ModelAndView modelAndView = new ModelAndView("admin/category/list");
		modelAndView.addObject("cList", categoryService.get());
		return modelAndView;
	}
	
	@GetMapping("admin/category/report")
	public ModelAndView report(@RequestParam("id") long id) {
		Set<Product> pSet = categoryService.findById(id).get().getProducts();
		List<Product> pList = new ArrayList<>(pSet);
		ModelAndView modelAndView = new ModelAndView("admin/product/report");
		modelAndView.addObject("pList", pList);
		return modelAndView;
	}
}
