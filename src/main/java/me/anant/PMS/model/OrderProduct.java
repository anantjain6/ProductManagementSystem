package me.anant.PMS.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Domain model describing OrderProduct mapping.
 * This represents mapping between an Order and its corresponding product.
 */
@Entity
public class OrderProduct implements Serializable {
	@Id
    @ManyToOne
    @JoinColumn
	private Order order;
	
	@Id
    @ManyToOne
    @JoinColumn
	private Product product;

	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	private int buyqty;

	private double lineItemPrice;
	
	public OrderProduct() {
		// TODO Auto-generated constructor stub
	}
	
    public OrderProduct(Product product, int buyqty) {
		super();
		this.product = product;
		this.buyqty = buyqty;
		this.lineItemPrice = product.getProductPrice();
	}
    
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getBuyqty() {
		return buyqty;
	}

	public void setBuyqty(int buyqty) {
		this.buyqty = buyqty;
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

	public double getLineItemPrice() {
		return lineItemPrice;
	}

	public void setLineItemPrice(double lineItemPrice) {
		this.lineItemPrice = lineItemPrice;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProduct)) return false;
        OrderProduct that = (OrderProduct) o;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        		product.getProductName(), 
        		product.hashCode(), 
        		buyqty
        );
    }
}
