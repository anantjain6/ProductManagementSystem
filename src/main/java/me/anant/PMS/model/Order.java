package me.anant.PMS.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Domain model describing Order Entity.
 */
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProduct;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	private String status;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(User user, String status, Set<OrderProduct> orderProduct) {
		this.user = user;
		this.status = status;
		for(OrderProduct op : orderProduct) op.setOrder(this);
		this.orderProduct = orderProduct;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<OrderProduct> getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(Set<OrderProduct> orderProduct) {
		this.orderProduct = orderProduct;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
