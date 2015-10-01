/**
 * 
 */
package com.packt.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.api.OrderRepository;

/**
 * @author herb
 *
 */
public class InMemoryOrderRepositoryImpl implements OrderRepository {
	
	private Map<Long, Order> listOfOrders;
	private long nextOrderId;
	
	public InMemoryOrderRepositoryImpl() {
		listOfOrders = new HashMap<Long, Order>();
		nextOrderId = 1000;
	}

	@Override
	public Long saveOrder(Order order) {
		order.setOrderId(getNextOrderId());
		listOfOrders.put(order.getOrderId(), order);
		return order.getOrderId();
	}

	private Long getNextOrderId() {

		return nextOrderId++;
	}

	
}
