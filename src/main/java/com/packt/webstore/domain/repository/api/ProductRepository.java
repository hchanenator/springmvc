package com.packt.webstore.domain.repository.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.packt.webstore.domain.Product;

public interface ProductRepository {
	
	List<Product> getAllProducts();
	
	Product getProductById(String productId);
	
	List<Product> getProductsByCategory(String category);
	
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	List<Product> getProductsByManufacturer(String manufacturer);
	
	Set<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilterParams);
	
	void addProduct(Product product);
	
}
