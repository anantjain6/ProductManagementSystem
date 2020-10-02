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

/**
 * This ProductCategoryController is responsible to :
 * <ul>
 *     <li>View the categories of a Product</li>
 *     <li>Generate the report of Product and its Categories</li>
 * </ul>
 */
@Controller
public class ProductCategoryController {
	@Autowired
	ProductCategoryService categoryService;

	/**
	 * This GET api is responsible to view the categories of a Product which is used by Admin.
	 * @return
	 */
	@GetMapping("admin/category/list")
	public ModelAndView categoryList() {
		ModelAndView modelAndView = new ModelAndView("admin/category/list");
		modelAndView.addObject("cList", categoryService.get());
		return modelAndView;
	}

	/**
	 * This GET api is responsible to generate the report used by Admin.
	 * @param id ModelAndView
	 * @return
	 */
	@GetMapping("admin/category/report")
	public ModelAndView report(@RequestParam("id") long id) {
		Set<Product> pSet = categoryService.findById(id).get().getProducts();
		List<Product> pList = new ArrayList<>(pSet);
		ModelAndView modelAndView = new ModelAndView("admin/product/report");
		modelAndView.addObject("pList", pList);
		return modelAndView;
	}
}
