package me.anant.PMS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import me.anant.PMS.dao.ProductRepo;
import me.anant.PMS.model.Product;

@Controller
public class ProductController {
	@Autowired
	ProductRepo productRepo;
	
	@PostMapping("/admin/product/add")
	public String add(Product product) {
		productRepo.save(product);
		return "admin/product/add";
	}
	
	@GetMapping("/admin/product/list")
	public ModelAndView list() {
		List<Product> pList = (List<Product>) productRepo.findAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/product/list");
		modelAndView.addObject("pList", pList);
		return modelAndView;
	}
	
//	@GetMapping("/admin/product/list")
//	public String list(Model model) {
//		List<Product> pList = (List<Product>) productRepo.findAll();
//		model.addAttribute("pList", pList)
//		return "/admin/product/list";
//	}
}
