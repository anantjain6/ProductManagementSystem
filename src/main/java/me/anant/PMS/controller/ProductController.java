package me.anant.PMS.controller;

import java.util.*;

import javax.validation.Valid;

import me.anant.PMS.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.anant.PMS.model.Product;
import me.anant.PMS.service.ProductCategoryService;
import me.anant.PMS.service.ProductService;

/**
 * This Controller is responsible for CRUD operations related to Products.
 */
@Controller
@RequestMapping("/admin/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductCategoryService categoryService;

	/**
	 * This api is responsible to fetch the model and view for add Product.
	 * @return ModelAndView
	 */
	@GetMapping("/add")
	public ModelAndView addView() {
		ModelAndView modelAndView = new ModelAndView("admin/product/add");
		modelAndView.addObject("pcList", categoryService.get());
		modelAndView.addObject("command", new Product());
		return modelAndView;
	}
	
	/**
	 * This api is responsible to add Product.
	 * @param product
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return String redirect path
	 */
	@PostMapping("/add")
	public String add(@ModelAttribute("command") @Valid Product product, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("pcList", categoryService.get());
			return "admin/product/add";
		}
		productService.save(product);
		redirectAttributes.addFlashAttribute("msg", "Product added successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/product/add";
	}

	@GetMapping
	public String index() {
		return "admin/product/list";
	}

  /**
	 * This Get api is responsible to view Product List
	 * @return ModelAndView
	 */
	@GetMapping("/list")
	public ResponseEntity<List<Product>> list() {
		List<Product> pList = productService.get();
		return ResponseEntity.ok(pList);
	}

	/**
	 * This GET api is responsible to delete the product.
	 * @param id
	 * @param redirectAttributes
	 * @return redirect path
	 */
	@GetMapping("/admin/product/delete")
	public String list(@RequestParam("id") long id,
			final RedirectAttributes redirectAttributes) {
		productService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "Product deleted successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/product/list";
	}

	/**
	 * This api is responsible to fetch the model and view for update Products Screen.
	 * @param id
	 * @return ModelAndView
	 */
	@GetMapping("/admin/product/update")
	public ModelAndView updateView(long id) throws ProductNotFoundException {
		Optional<Product> optional = productService.findById(id);
		Product product = optional.get();
		ModelAndView modelAndView = new ModelAndView("admin/product/add");
		modelAndView.addObject("command", product);
		modelAndView.addObject("pcList", categoryService.get());
		return modelAndView;
	}

	/**
	 * This POST api is responsible to update the Product.
	 * @param product
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return String redirect path
	 */
	@PostMapping("/admin/product/update")
	public String updateView(@ModelAttribute("command") @Valid Product product, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("pcList", categoryService.get());
			return "admin/product/add";
		}
		productService.save(product);
		redirectAttributes.addFlashAttribute("msg", "Product Updated successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/product/list";
	}

	/**
	 * This api is responsible to generate report related to Product.
	 * @return ModelAndView
	 */
	@GetMapping("/report")
	public ModelAndView report() {
		ModelAndView modelAndView = new ModelAndView("admin/product/report");
		modelAndView.addObject("pList", productService.get());
		return modelAndView;
	}
}
