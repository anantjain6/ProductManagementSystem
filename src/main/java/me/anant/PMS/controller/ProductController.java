package me.anant.PMS.controller;

import java.util.*;

import javax.validation.Valid;

import me.anant.PMS.dto.CategoryDto;
import me.anant.PMS.dto.ProductDto;
import me.anant.PMS.exceptions.ProductNotFoundException;
import me.anant.PMS.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	public String addView() {
		return "/admin/product/add";
	}
	
	/**
	 * This api is responsible to add Product.
	 * @param productDto - product data
	 * @return 201 created
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<Void> add(@RequestBody ProductDto productDto) {
		Product product = new Product();
		product.setProductName(productDto.getName());
		product.setProductPrice(productDto.getPrice());
		product.setProductQty(productDto.getQuantity());
		product.setCategory(categoryService.findById(productDto.getCategory()).get());
		productService.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * This endpoint returns products index page.
	 * @return product list uri string
	 */
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
	 * This DELETE api is responsible to delete the product.
	 * @param id - unique id of the product
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> list(@PathVariable("id") long id) {
		productService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/**
	 * This api is responsible to fetch the model and view for update Products Screen.
	 * @param id - unique id of the product
	 * @return ResponseEntity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> fetchProduct(@PathVariable long id) {
		try {
			Product product = productService.findById(id);
			ProductDto dto = productService.toDto(product);
			return ResponseEntity.ok(dto);
		} catch (ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	/**
	 * This endpoint return product add form.
	 * @return string - product add form uri
	 */
	@GetMapping("/update/{id}")
	public String showUpdateForm() {
		return "/admin/product/add";
	}

	/**
	 * This PUT api is responsible to update the Product.
	 * @return ResponseEntity
	 */
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateProduct(@RequestBody ProductDto productDto) {
		try {
			final Product product = productService.findById(productDto.getId());
			product.setProductName(productDto.getName());
			product.setProductPrice(productDto.getPrice());
			product.setProductQty(productDto.getQuantity());
			Optional<ProductCategory> productCategory = categoryService.findById(productDto.getCategory());
			productCategory.ifPresent(product::setCategory);
			productService.save(product);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	/**
	 * This endpoint returns list of categories
	 * @return list of categories
	 */
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> fetchCategories() {
		List<ProductCategory> categories = categoryService.get();
		List<CategoryDto> result = new ArrayList<>();
		categories.forEach(category -> {
			result.add(productService.toDto(category));
		});
		return ResponseEntity.ok(result);
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
