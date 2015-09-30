/**
 * 
 */
package com.packt.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.repository.api.CartRepository;

/**
 * @author herb
 *
 */
@Repository
public class InMemoryCartRepository implements CartRepository {

	private Map<String, Cart> listOfCarts;

	public InMemoryCartRepository() {
		listOfCarts = new HashMap<String, Cart>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.packt.webstore.domain.repository.api.CartRepository#create(com.packt.
	 * webstore.domain.Cart)
	 */
	@Override
	public Cart create(Cart cart) {
		if (listOfCarts.keySet().contains(cart.getCartId())) {
			throw new IllegalArgumentException(String
					.format("Cannot create a cart.  A cart with the give id (%) aldrady exist", cart.getCartId()));
		}

		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.packt.webstore.domain.repository.api.CartRepository#read(java.lang.
	 * String)
	 */
	@Override
	public Cart read(String cartId) {

		return listOfCarts.get(cartId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.packt.webstore.domain.repository.api.CartRepository#update(java.lang.
	 * String, com.packt.webstore.domain.Cart)
	 */
	@Override
	public void update(String cartId, Cart cart) {
		if (!listOfCarts.containsKey(cartId)) {
			throw new IllegalArgumentException(
					String.format("Cannot update cart.  A cart with the give id (%) does not exist", cart.getCartId()));
		}

		listOfCarts.put(cartId, cart);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.packt.webstore.domain.repository.api.CartRepository#delete(java.lang.
	 * String)
	 */
	@Override
	public void delete(String cartId) {
		if (!listOfCarts.containsKey(cartId)) {
			throw new IllegalArgumentException(String.format("Cannot delete cart.  A cart with the give id (%) does not exist", cartId));
		}
		
		listOfCarts.remove(cartId);

	}

}
