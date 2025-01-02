package com.java.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.java.customer.exception.CustomerNotFoundException;
import com.java.customer.model.Customer;
import com.java.customer.model.MyTransaction;
import com.java.customer.repository.CustomerRepository;
import com.java.customer.service.RewardsService;

/**
 * 
 * @author mmasali
 *
 */
public class CustomerRewardProgramApplicationTests {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private RewardsService customerService;

	private Customer customer1;
	private Customer customer2;
    private List<Customer> customerList;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
       
		customerList = new ArrayList<>();
        customer1 = new Customer();
        customer1.setId(1);
        customer1.setTransactions(new ArrayList<>());

        customer2 = new Customer();
        customer2.setId(2);
        customer2.setTransactions(new ArrayList<>());

        customerList.add(customer1);
        customerList.add(customer2);
	}

	@Test
	public void testGetCustomerById_CustomerExists() {
		when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));

		Customer result = customerService.getCustomerById(1);

		assertNotNull(result);
		assertEquals(1, result.getId().longValue());
	}

	@Test
	public void testGetCustomerById_CustomerNotFound() {
		when(customerRepository.findById(1)).thenReturn(Optional.empty());

		try {
			customerService.getCustomerById(1);
			fail("Expected CustomerNotFoundException to be thrown");
		} catch (CustomerNotFoundException e) {
			assertEquals("No Customer found with id-1", e.getMessage());
		}

	}

	@Test
	public void testGetCustomerById_NullCustomerId() {

		try {
			customerService.getCustomerById(null);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Only Positive Numbers please!", e.getMessage());
		}
	}

	@Test
	public void testGetCustomerById_NegativeCustomerId() {
		
		try {
			customerService.getCustomerById(-1);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Only Positive Numbers please!", e.getMessage());
		}
	}

	@Test
	public void testGetCustomerById_CustomerWithNoTransactions() {
		when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));

		Customer result = customerService.getCustomerById(1);

		assertNotNull(result);
		assertEquals(0, result.getTransactions().size());
	}
	

    @Test
    public void testGetCustomerById_CustomerWithMultipleTransactions() {
        MyTransaction transaction1 = new MyTransaction();
        MyTransaction transaction2 = new MyTransaction();
        transaction1.setTotal(100.0);
        transaction2.setTotal(200.0);
        customer1.getTransactions().add(transaction1);
        customer1.getTransactions().add(transaction2);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));

        Customer result = customerService.getCustomerById(1);

        assertNotNull(result);
        assertEquals(2, result.getTransactions().size());
    }
    
    //Get all customers
    @Test
    public void testGetCustomerAll_CustomersExist() {
        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> result = customerService.getCustomerAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetCustomerAll_NoCustomers() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        try {
            customerService.getCustomerAll();
            fail("Expected CustomerNotFoundException to be thrown");
        } catch (CustomerNotFoundException e) {
            assertEquals("No Customers found", e.getMessage());
        }

    }

    @Test
    public void testGetCustomerAll_CustomersWithTransactions() {
        MyTransaction transaction1 = new MyTransaction();
        MyTransaction transaction2 = new MyTransaction();
        transaction1.setTotal(100.0);
        transaction2.setTotal(200.0);
        customerList.get(0).getTransactions().add(transaction1);
        customerList.get(1).getTransactions().add(transaction2);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> result = customerService.getCustomerAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getTransactions().size());
        assertEquals(1, result.get(1).getTransactions().size());

    }
}
