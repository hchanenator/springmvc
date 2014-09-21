package com.packt.webstore.domain.repository.api;

import java.util.List;

import com.packt.webstore.domain.Customer;

public interface CustomerRepository {
	
	List<Customer> getAllCustomers();

}
