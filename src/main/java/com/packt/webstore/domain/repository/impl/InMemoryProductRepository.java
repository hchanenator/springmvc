package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.api.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	
	private List<Product> listOfProducts = new ArrayList<Product>();

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Product getProductById(String productId) {
		Product productById = null;
		
		for(Product product : listOfProducts) {
			if(product != null && product.getProductId() != null && product.getProductId().equalsIgnoreCase(productId)) {
				productById = product;
				break;
			}
		}
		
		if(productById == null) {
			throw new IllegalArgumentException("No products found with the product id: " + productId);
		}
		
		return productById;
	}

}
