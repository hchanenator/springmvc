package com.packt.webstore.service.api;

public interface OrderService {
	
	void processOrder(String productId, long quantity);

}
