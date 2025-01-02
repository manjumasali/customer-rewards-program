package com.java.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.customer.model.Customer;

/**
 * 
 * @author mmasali
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
