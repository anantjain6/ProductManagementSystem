package me.anant.PMS.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.anant.PMS.model.Product;
import me.anant.PMS.service.ProductCategoryService;
import me.anant.PMS.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductCategoryService categoryService;
	
	@GetMapping("/admin/product/add")
	public ModelAndView addView() {
		ModelAndView modelAndView = new ModelAndView("admin/product/add");
		modelAndView.addObject("pcList", categoryService.get());
		return modelAndView;
	}
	
	@PostMapping("/admin/product/add")
	public String add(Product product, @RequestParam("productCategory") long category, Model model, final RedirectAttributes redirectAttributes) {
		product.setCategory(categoryService.findById(category).get());
		productService.save(product);
		redirectAttributes.addFlashAttribute("msg", "Product added successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/product/list";
	}
	
	@GetMapping("/admin/product/list")
	public ModelAndView list() {
		List<Product> pList = productService.get();
		ModelAndView modelAndView = new ModelAndView("/admin/product/list");
		modelAndView.addObject("pList", pList);
		return modelAndView;
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
		modelAndView.addObject("product", product);
		modelAndView.addObject("pcList", categoryService.get());
		return modelAndView;
	}
	
	@PostMapping("/admin/product/update")
	public ModelAndView updateView(Product product, @RequestParam("productCategory") long category) {
		product.setCategory(categoryService.findById(category).get());
		productService.save(product);
		ModelAndView modelAndView = new ModelAndView("admin/product/add");
		modelAndView.addObject("product", product);
		modelAndView.addObject("pcList", categoryService.get());
		modelAndView.addObject("msg", "Product Updated successfully.");
		modelAndView.addObject("class", "alert-success");
		return modelAndView;
	}
	
	@GetMapping("/admin/product/report")
	public ModelAndView report() {
		ModelAndView modelAndView = new ModelAndView("admin/product/report");
		modelAndView.addObject("pList", productService.get());
		return modelAndView;
	}
}
