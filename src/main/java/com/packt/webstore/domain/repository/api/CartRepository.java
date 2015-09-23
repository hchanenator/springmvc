/**
 * 
 */
package com.packt.webstore.domain.repository.api;

import com.packt.webstore.domain.Cart;

/**
 * @author herb
 *
 */
public interface CartRepository {

	Cart create(Cart cart);

	Cart read(String cartId);

	void update(String cartId, Cart cart);

	void delete(String cartId);
}
