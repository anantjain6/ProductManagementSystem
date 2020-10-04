package me.anant.PMS.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
		modelAndView.addObject("command", new Product());
		return modelAndView;
	}
	
	@PostMapping("/admin/product/add")
	public String add(@ModelAttribute("command") @Valid Product product, @RequestParam("imageFile") MultipartFile imageFile, 
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("pcList", categoryService.get());
			return "admin/product/add";
		}
		
		if(imageFile.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg", "Please select product picture");
			redirectAttributes.addFlashAttribute("class", "alert-danger");
			return "redirect:/admin/product/add";
		}else {
			String filePath = productService.saveImage(imageFile);
			product.setImageName(filePath);
			productService.save(product);
			redirectAttributes.addFlashAttribute("msg", "Product added successfully");
			redirectAttributes.addFlashAttribute("class", "alert-success");
			return "redirect:/admin/product/add";
		}
		
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
	
	@GetMapping("/admin/product/report")
	public ModelAndView report() {
		ModelAndView modelAndView = new ModelAndView("admin/product/report");
		modelAndView.addObject("pList", productService.get());
		return modelAndView;
	}
}
