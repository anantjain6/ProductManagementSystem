package me.anant.PMS.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Domain model describing Product and its attributes.
 */
@Entity
public class Product {
	@Id
	@GeneratedValue
	private long productId;

	@NotBlank(message="Product name can not be empty")
	@Size(min = 3, message="Product name should be of minimum 3 character")
	private String productName;
	
	@NotNull
	@Min(value=0, message="Product price can not be negative value.")
	private float productPrice;
	
	@NotNull
	@Min(value=0, message="Product quantity can not be negative")
	@Max(value=50, message="Product quantity can be maximum 50.")
	private int productQty;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProduct;

	@ManyToOne(fetch = FetchType.LAZY)
    private ProductCategory category;

	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(long productId) {
		super();
		this.productId = productId;
	}

	public Product(String productName, float productPrice, int productQty, ProductCategory category) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQty = productQty;
		this.category = category;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
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

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
}
