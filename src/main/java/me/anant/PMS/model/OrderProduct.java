package me.anant.PMS.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderProduct implements Serializable {
	@Id
    @ManyToOne
    @JoinColumn
	Order order;
	
	@Id
    @ManyToOne
    @JoinColumn
	Product product;
	
	int buyqty;
	
	public OrderProduct() {
		// TODO Auto-generated constructor stub
	}
	
    public OrderProduct(Product product, int buyqty) {
		super();
		this.product = product;
		this.buyqty = buyqty;
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
