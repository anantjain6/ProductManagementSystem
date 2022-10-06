package me.anant.PMS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import me.anant.PMS.Helper.ModelAndViewProviderHelper;
import me.anant.PMS.RequestMappingConstants.ViewConstants;
import me.anant.PMS.exceptions.ProductNotFoundException;
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
import me.anant.PMS.service.ProductCategoryService;
import me.anant.PMS.service.ProductService;

/**
 * This Controller is responsible for CRUD operations related to Products.
 */
@Controller
public class ProductController {
	ProductService productService;
	ProductCategoryService categoryService;
	ModelAndViewProviderHelper modelAndViewProviderHelper;

	public ProductController(ProductService productService,ProductCategoryService productCategoryService,ModelAndViewProviderHelper modelAndViewProviderHelper)
	{
		this.productService=productService;
		this.categoryService=productCategoryService;
		this.modelAndViewProviderHelper=modelAndViewProviderHelper;
	}

	/**
	 * This api is responsible to fetch the model and view for add Product.
	 * @return ModelAndView
	 */
	@GetMapping("/admin/product/add")
	public ModelAndView addView() {
		Map<String,Object> attributeNameAndValueMap=new HashMap<>();
		attributeNameAndValueMap.put("pcList", categoryService.get());
		attributeNameAndValueMap.put("command", new Product());
		return this.modelAndViewProviderHelper.generateModelAndView(ViewConstants.ADMIN_PRODUCT_ADD,attributeNameAndValueMap);
	}

	/**
	 * This api is responsible to add Product.
	 * @param product
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return String redirect path
	 */
	@PostMapping("/admin/product/add")
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

	/**
	 * This Get api is responsible to view Product List
	 * @return ModelAndView
	 */
	@GetMapping("/admin/product/list")
	public ModelAndView list() {
		List<Product> pList = productService.get();
		return this.modelAndViewProviderHelper.generateModelAndView(ViewConstants.ADMIN_PRODUCT_LIST,"pList", pList);
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

		Product product = productService.findById(id);
		Map<String,Object> attributeNameAndValueMap=new HashMap<>();
		attributeNameAndValueMap.put("command", product);
		attributeNameAndValueMap.put("pcList", categoryService.get());
		return this.modelAndViewProviderHelper.generateModelAndView(ViewConstants.ADMIN_PRODUCT_ADD,attributeNameAndValueMap);
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
	@GetMapping("/admin/product/report")
	public ModelAndView report() {
		return this.modelAndViewProviderHelper.generateModelAndView(ViewConstants.ADMIN_PRODUCT_REPORT,"pList", productService.get());
	}
}
