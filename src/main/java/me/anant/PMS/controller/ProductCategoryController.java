package me.anant.PMS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.anant.PMS.model.Product;
import me.anant.PMS.model.ProductCategory;
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
	
	@GetMapping("/admin/product_category/add")
	public ModelAndView addView() {
		ModelAndView modelAndView = new ModelAndView("admin/product_category/add");
		modelAndView.addObject("command",new ProductCategory());
		return modelAndView;
	}

	@PostMapping("/admin/product_category/add")
	public String add(@ModelAttribute("command") @Valid ProductCategory productCategory, BindingResult result, Model model, final RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			return "admin/product_category/add";
		}
		categoryService.save(productCategory);
		redirectAttributes.addFlashAttribute("msg", "Product Category added successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/product_category/list";
	}
 
	@GetMapping("admin/product_category/list")
	public ModelAndView productCategoryList() {
		ModelAndView modelAndView = new ModelAndView("admin/product_category/list");
		modelAndView.addObject("cList", categoryService.get());
		return modelAndView;
	}

	@GetMapping("/admin/product_category/update")
	public ModelAndView updateView(long id) {
		Optional<ProductCategory> optional = categoryService.findById(id);
		ProductCategory productCategory = optional.get();
		ModelAndView modelAndView = new ModelAndView("admin/product_category/add");
		modelAndView.addObject("command", productCategory);
		return modelAndView;
	}
	
	@PostMapping("/admin/product_category/update")
	public String updateView(@ModelAttribute("command") @Valid ProductCategory productCategory, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("pcList", categoryService.get());
			return "admin/product_category/add";
		}
		categoryService.save(productCategory);
		redirectAttributes.addFlashAttribute("msg", "Product Category Updated successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/product_category/list";
	}
	
	@GetMapping("/admin/product_category/delete")
	public String list(@RequestParam("id") long id,
			final RedirectAttributes redirectAttributes) {
		categoryService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "Product Category deleted successfully");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/product_category/list";
	}

	/**
	 * This GET api is responsible to view the categories of a Product which is used by Admin.
	 * @return ModelAndView
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
	 * @return ModelAndView
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
