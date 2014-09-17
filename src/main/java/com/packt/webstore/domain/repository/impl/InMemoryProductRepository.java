package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.api.ProductRepository;

public class InMemoryProductRepository implements ProductRepository {
	
	private List<Product> listOfProducts = new ArrayList<Product>();

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
