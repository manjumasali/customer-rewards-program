package com.java.customer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * 
 * @author mmasali
 *
 */
@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MyTransaction> transactions;
	private Long rewardPoints;
	private Double totalPurchases;
	
	public Customer() {
		super();
	}
	public Customer(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MyTransaction> getTransactions() {
		return transactions;
	}
	public Long getRewardPoints() {
		return rewardPoints;
	}
	public Double getTotalPurchases() {
		return totalPurchases;
	}
	public void setTransactions(List<MyTransaction> transactions) {
		this.transactions = transactions;
	}

	public void setRewardPoints(Long rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public void setTotalPurchases(Double totalPurchases) {
		this.totalPurchases = totalPurchases;
	}

	
	
}
