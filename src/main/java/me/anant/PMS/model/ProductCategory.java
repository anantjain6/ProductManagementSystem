package me.anant.PMS.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Domain model describing ProductCategory Entity.
 */
@Entity
public class ProductCategory {
	@Id
	@GeneratedValue
	long id;
	
	String name;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)		
	Set<Product> products;
	
	public ProductCategory() {
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(long id) {
		super();
		this.id = id;
	}

	public ProductCategory(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
