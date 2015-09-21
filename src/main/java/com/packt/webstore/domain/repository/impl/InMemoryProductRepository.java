package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.api.ProductRepository;
import com.packt.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	private List<Product> listOfProducts = new ArrayList<Product>();

	public InMemoryProductRepository() {
		createProducts();
	}

	private void createProducts() {
		Product iPhone6 = new Product("P1234", "iPhone 6", new BigDecimal("499.99"));
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

		Product satellite = new Product("P9870", "Toshiba Tecra R950 Laptop", new BigDecimal("3026.99"));
		satellite.setDescription("An awesome gaming machine!");
		satellite.setCategory("Laptop");
		satellite.setManufacturer("Toshiba");
		satellite.setUnitsInStock(300);

		Product iPad = new Product("P1432", "iPad Mini Air - 32 GB", new BigDecimal("799.99"));
		iPad.setDescription("Might as well be called an iPhone!");
		iPad.setCategory("Tablet");
		iPad.setManufacturer("Apple");
		iPad.setUnitsInStock(750);

		Product nexus = new Product("P1111", "Google Nexus tablet", new BigDecimal("259.99"));
		nexus.setDescription("Simple tablet");
		nexus.setCategory("Tablet");
		nexus.setManufacturer("Google");
		nexus.setUnitsInStock(400);

		Product chromebook = new Product("P1120", "Google Chromebook", new BigDecimal("159.99"));
		chromebook.setDescription("Simple laptop");
		chromebook.setCategory("Laptop");
		chromebook.setManufacturer("Google");
		chromebook.setUnitsInStock(999);
		
		Product macbookpro = new Product("P7888", "Apple MacBook Pro", new BigDecimal("3299.00"));
		macbookpro.setDescription("15-inch Retina display w/512GB SSD, 8GB RAM, i7 + Turbo Boost to 4.0GHz.  Nuff said.");
		macbookpro.setCategory("Laptop");
		macbookpro.setManufacturer("Apple");
		macbookpro.setUnitsInStock(120);

		listOfProducts.add(iPhone6);
		listOfProducts.add(galaxyS5);
		listOfProducts.add(latitude);
		listOfProducts.add(satellite);
		listOfProducts.add(iPad);
		listOfProducts.add(nexus);
		listOfProducts.add(chromebook);
		listOfProducts.add(macbookpro);

	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return listOfProducts;
	}

	public Product getProductById(String productId) {
		Product productById = null;

		for (Product product : listOfProducts) {
			if (product != null && product.getProductId() != null
					&& product.getProductId().equalsIgnoreCase(productId)) {
				productById = product;
				break;
			}
		}

		if (productById == null) {
			throw new ProductNotFoundException(productId);
		}

		return productById;
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		List<Product> productsByCategory = new ArrayList<Product>();

		for (Product product : listOfProducts) {
			if (category.equalsIgnoreCase(product.getCategory())) {
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();

		Set<String> criteria = filterParams.keySet();

		if (criteria.contains("brand")) {
			for (String brandName : filterParams.get("brand")) {
				for (Product product : listOfProducts) {
					if (brandName.equalsIgnoreCase(product.getManufacturer())) {
						productsByBrand.add(product);
					}
				}
			}
		}

		if (criteria.contains("category")) {
			for (String category : filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}
		
		productsByCategory.retainAll(productsByBrand);
		return productsByCategory;
	}

	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
		List<Product> productsByManufacturer = new ArrayList<Product>();

		for (Product product : listOfProducts) {
			if (manufacturer.equalsIgnoreCase(product.getManufacturer())) {
				productsByManufacturer.add(product);
			}
		}

		return productsByManufacturer;
	}

	@Override
	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilterParams) {
		Set<Product> productsByPriceRangeLow = new HashSet<Product>();
		Set<Product> productsByPriceRange = new HashSet<Product>();
		
		Set<String> criteria = priceFilterParams.keySet();
		
		if (criteria.contains("low")) {
			for (String lowPrice : priceFilterParams.get("low")) {
				BigDecimal low = new BigDecimal(lowPrice);
				for (Product product : listOfProducts) {
					if (product.getUnitPrice().compareTo(low) > 0) {
						productsByPriceRangeLow.add(product);
					}
				}
			}
		}
		
		if (criteria.contains("high")) {
			for (String highPrice : priceFilterParams.get("high")) {
				BigDecimal high = new BigDecimal(highPrice);
				for (Product product : listOfProducts) {
					if (product.getUnitPrice().compareTo(high) < 0) {
						productsByPriceRange.add(product);						
					}
				}
			}
		}
		
		productsByPriceRange.retainAll(productsByPriceRangeLow);
		
		return productsByPriceRange;
	}
	
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}

}
