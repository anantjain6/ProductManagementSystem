package me.anant.PMS.controller;

import java.util.*;

import javax.validation.Valid;

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

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductCategoryService categoryService;
	
	@GetMapping("/add")
	public ModelAndView addView() {
		ModelAndView modelAndView = new ModelAndView("admin/product/add");
		modelAndView.addObject("pcList", categoryService.get());
		modelAndView.addObject("command", new Product());
		return modelAndView;
	}
	
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

	@GetMapping("/list")
	public ResponseEntity<List<Product>> list() {
		List<Product> pList = productService.get();
		return ResponseEntity.ok(pList);
	}
	
	@GetMapping("/admin/product/delete")
	public String list(@RequestParam("id") long id,
			final RedirectAttributes redirectAttributes) {
		productService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "Product deleted successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/product/list";
	}
	
	@GetMapping("/admin/product/update")
	public ModelAndView updateView(long id) {
		Optional<Product> optional = productService.findById(id);
		Product product = optional.get();
		ModelAndView modelAndView = new ModelAndView("admin/product/add");
		modelAndView.addObject("command", product);
		modelAndView.addObject("pcList", categoryService.get());
		return modelAndView;
	}
	
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
	
	@GetMapping("/report")
	public ModelAndView report() {
		ModelAndView modelAndView = new ModelAndView("admin/product/report");
		modelAndView.addObject("pList", productService.get());
		return modelAndView;
	}
}
