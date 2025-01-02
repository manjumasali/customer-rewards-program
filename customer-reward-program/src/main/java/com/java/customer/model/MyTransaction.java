package com.java.customer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author mmasali
 *
 */
@Entity
public class MyTransaction{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn //foreign key to customer
	private Customer customer;
	private Double total; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date save_date;
	protected Long points;
	

	public MyTransaction() {
		super();
	}
	public MyTransaction(Long id, Customer customer, Double total, Date date) {
		super();
		this.id = id;
		this.customer = customer;
		this.total = total; 
		this.save_date = date;
	}
 
 
	public Date getSave_date() {
		return save_date;
	}
	public void setSave_date(Date save_date) {
		this.save_date = save_date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
  
	@Override
	public String toString() {
		return String.format("MyTransaction [id=%s, customer=%s, total=%s, save_date=%s]", id, customer,
				total, save_date);
	}
 
 
	
}
