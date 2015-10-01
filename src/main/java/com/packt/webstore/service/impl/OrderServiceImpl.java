package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.api.OrderRepository;
import com.packt.webstore.domain.repository.api.ProductRepository;
import com.packt.webstore.service.api.CartService;
import com.packt.webstore.service.api.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository OrderRepository;
	
	@Autowired
	private CartService cartService;

	@Override
	public void processOrder(String productId, long count) {
		Product productById = productRepository.getProductById(productId);
		
		if (productById.getUnitsInStock() < count) {
			throw new IllegalArgumentException("There is not enough inventory to fulfill the order.  Available units in stock: " + productById.getUnitsInStock());
		}
		
		productById.setUnitsInStock(productById.getUnitsInStock() - count);

	}

	@Override
	public Long saveOrder(Order order) {
		Long orderId = OrderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}
	
	

}
