package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.api.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	
	private List<Product> listOfProducts = new ArrayList<Product>();
	
	public InMemoryProductRepository() {
		createProducts();
	}
	
	private void createProducts() {
		Product iPhone6 = new Product("P1234", "iPhone6", new BigDecimal("499.99"));
		iPhone6.setDescription("The latest and greatest!");
		iPhone6.setCategory("SmartPhone");
		iPhone6.setManufacturer("Apple");
		iPhone6.setUnitsInStock(200);
		
		Product galaxyS5 = new Product("P3456", "Samsung Galaxy S5", new BigDecimal("349.99"));
		galaxyS5.setDescription("A very good phablet!");
		galaxyS5.setCategory("SmartPhone");
		galaxyS5.setManufacturer("Samsung");
		galaxyS5.setUnitsInStock(1000);
		
		Product latitude = new Product("P7890", "Dell Latitude X850", new BigDecimal("1099.99"));
		latitude.setDescription("A Latitude with an atitude!");
		latitude.setCategory("Laptop");
		latitude.setManufacturer("Dell");
		latitude.setUnitsInStock(500);
		
	}

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
