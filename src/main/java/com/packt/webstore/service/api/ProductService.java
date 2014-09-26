package com.packt.webstore.service.api;

import java.util.List;

import com.packt.webstore.domain.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	List<Product> getProductsByCategory(String category);

}
