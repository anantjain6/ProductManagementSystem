package me.anant.PMS.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue
	long id;
	Date date;
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinColumn(name="order_id")
	Set<OrderProduct> orderProducts;
	@ManyToOne(fetch = FetchType.LAZY)
    private User user;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(long id, Date date, Set<OrderProduct> orderProducts, User user) {
		super();
		this.id = id;
		this.date = date;
		this.orderProducts = orderProducts;
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
