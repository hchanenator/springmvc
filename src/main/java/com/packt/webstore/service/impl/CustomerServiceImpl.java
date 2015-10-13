package com.packt.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.api.CustomerRepository;
import com.packt.webstore.exception.CustomerNotFoundException;
import com.packt.webstore.service.api.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepository.getAllCustomers();
	}

	@Override
	public Customer findCustomer(String customerId) {
		List<Customer> customers = getAllCustomers();
		Customer foundCustomer = null;
		
		for (Customer customer : customers) {
			if (customer.getCustomerId().equals(customerId)) {
				foundCustomer = customer;
				break;
			}
		}
		
		return foundCustomer;
	}

}
