/**
 * 
 */
package com.packt.webstore.domain.repository.api;

import com.packt.webstore.domain.Order;

/**
 * @author herb
 *
 */
public interface OrderRepository {
	
	Long saveOrder(Order order);

}
