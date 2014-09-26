package com.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.api.ProductRepository;
import com.packt.webstore.service.api.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {

		return productRepository.getAllProducts();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {

		return productRepository.getProductsByCategory(category);
	}

	@Override
	public Set<Product> getProductsByFilter(
			Map<String, List<String>> filterParams) {

		return productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public Product getProductById(String productId) {
		
		return productRepository.getProductById(productId);
	}

}
