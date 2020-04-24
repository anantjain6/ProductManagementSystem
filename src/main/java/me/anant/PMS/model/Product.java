package me.anant.PMS.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue
	long productId;
	
	String productName;
	float productPrice;
	int productQty;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<OrderProduct> orderProduct;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, float productPrice, int productQty) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQty = productQty;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public Set<OrderProduct> getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(Set<OrderProduct> orderProduct) {
		this.orderProduct = orderProduct;
	}
}
