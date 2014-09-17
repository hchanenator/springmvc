package com.packt.webstore.domain.repository.api;

import java.util.List;

import com.packt.webstore.domain.Product;

public interface ProductRepository {
	
	List<Product> getAllProducts();

}
