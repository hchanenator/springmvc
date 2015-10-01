/**
 * 
 */
package com.packt.webstore.service.api;

import com.packt.webstore.domain.Cart;

/**
 * @author herb
 *
 */
public interface CartService {

	Cart create(Cart cart);

	Cart read(String cartId);

	void update(String cartId, Cart cart);

	void delete(String cartId);
	
	Cart validate(String cartId);
}
