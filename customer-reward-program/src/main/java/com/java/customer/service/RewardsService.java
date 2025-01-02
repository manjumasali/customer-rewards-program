package com.java.customer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.customer.exception.CustomerNotFoundException;
import com.java.customer.model.Customer;
import com.java.customer.model.MyTransaction;
import com.java.customer.repository.CustomerRepository;

/**
 * This is a service class that implements getCustomerById() method to get
 * customer based on Id passed to it.
 * 
 * @author mmasali
 * @version 1.0
 * @since 2014-12-23
 *
 */
@Service
public class RewardsService {
	private static final Logger logger = LoggerFactory.getLogger(RewardsService.class);

	@Autowired
	private CustomerRepository customerRepository;
 

	/**
	 * 
	 * @param customerId
	 * @return Customer object if customer found with customerId
	 * @throws CustomerNotFoundException if not found
	 */
	public Customer getCustomerById(Integer customerId) {
		logger.info("Info level - Inside getCustomerById");

		if(customerId == null || customerId < 0) {
			throw new IllegalArgumentException("Only Positive Numbers please!"); 
		}
		
		Customer customer;
		if (customerRepository.findById(customerId).isEmpty()) {
			throw new CustomerNotFoundException("No Customer found with id-" + customerId);
		} else {
			customer = customerRepository.findById(customerId).get();

			customer.setRewardPoints(getRewardPoints(customer.getTransactions()));
			customer.setTotalPurchases(getTotalPurchases(customer.getTransactions()));
		}

		return customer;
	}
	
	/**
	 * 
	 * @return list of customers
	 */
	public List<Customer> getCustomerAll() {
		List<Customer> customerList;
		if (customerRepository.findAll().isEmpty()) {
			throw new CustomerNotFoundException("No Customers found");
		} else {

			customerList = customerRepository.findAll();
			for (Customer customer : customerList) {
				customer.setRewardPoints(getRewardPoints(customer.getTransactions()));
				customer.setTotalPurchases(getTotalPurchases(customer.getTransactions()));
			}
		}

		return customerList;
	}

	/**
	 * 
	 * @param list
	 * @return sumReward - sum of all rewards points
	 */
	public Long getRewardPoints(List<MyTransaction> list) {
		logger.info("Info level - Inside getRewardPoints");

		if (list == null || list.isEmpty())
			return 0l;

		Long sumReward = 0L;
		for (MyTransaction myTransaction : list) {
			sumReward = sumReward + calculatePoints(myTransaction);
		}
		logger.info("Info level - " + sumReward);
		return sumReward;
	}

	/**
	 * 
	 * @param list
	 * @return totalSum - sum of all total
	 */
	public Double getTotalPurchases(List<MyTransaction> list) {
		logger.info("Info level - Inside getTotalPurchases");

		if (list == null || list.isEmpty())
			return 0D;

		Double totalSum = 0d;
		for (MyTransaction myTransaction : list) {
			totalSum = totalSum + myTransaction.getTotal();
		}
		logger.info("Info level - " + totalSum);
		return totalSum;
	}

	/**
	 * 
	 * @param myTransaction
	 * @return points - points calculated based on the condition.
	 */
	public Long calculatePoints(MyTransaction myTransaction) {
		logger.info("Info level - Inside calculatePoints");
		Long points = 0L;

		// calculate rewards points - for total sum > 50 and <=100
		if (myTransaction.getTotal() > 50 && myTransaction.getTotal() <= 100) {
			points = points + (myTransaction.getTotal().intValue() - 50) * 1;
		}
		// calculate rewards points - for total sum >100
		if (myTransaction.getTotal() > 100) {
			// 1 reward point for every dollar spent over $50
			points = points + 50;
			// 2 reward points for every dollar spent over $100
			points = points + (myTransaction.getTotal().intValue() - 100) * 2;
		}
		logger.info("Info level - Total Rewards " + points);
		return points;
	}



}
