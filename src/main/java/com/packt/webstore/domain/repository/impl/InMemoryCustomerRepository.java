package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.api.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
	
	private List<Customer> listOfCustomers = new ArrayList<Customer>();

	@Override
	public List<Customer> getAllCustomers() {
		Customer herb = new Customer("123", "Herb", "Chan");
		Customer karen = new Customer("124", "Karen", "Chan");
		Customer kaeley = new Customer("125", "Kaeley", "Chan");
		Customer nicole = new Customer("126", "Nicole", "Chan");
		
		listOfCustomers.add(herb);
		listOfCustomers.add(karen);
		listOfCustomers.add(kaeley);
		listOfCustomers.add(nicole);
		
		return listOfCustomers;
	}

}
