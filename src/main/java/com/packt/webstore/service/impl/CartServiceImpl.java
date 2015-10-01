/**ullu
 * 
 */
package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.repository.api.CartRepository;
import com.packt.webstore.exception.InvalidCartException;
import com.packt.webstore.service.api.CartService;

/**
 * @author herb
 *
 */
@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository; 

	/* (non-Javadoc)
	 * @see com.packt.webstore.service.api.CartService#create(com.packt.webstore.domain.Cart)
	 */
	@Override
	public Cart create(Cart cart) {
		
		return cartRepository.create(cart);
	}

	/* (non-Javadoc)
	 * @see com.packt.webstore.service.api.CartService#read(java.lang.String)
	 */
	@Override
	public Cart read(String cartId) {

		return cartRepository.read(cartId);
	}

	/* (non-Javadoc)
	 * @see com.packt.webstore.service.api.CartService#update(java.lang.String, com.packt.webstore.domain.Cart)
	 */
	@Override
	public void update(String cartId, Cart cart) {
		cartRepository.update(cartId, cart);

	}

	/* (non-Javadoc)
	 * @see com.packt.webstore.service.api.CartService#delete(java.lang.String)
	 */
	@Override
	public void delete(String cartId) {
		cartRepository.delete(cartId);
	}

	@Override
	public Cart validate(String cartId) {
		Cart cart = cartRepository.read(cartId);
		if (null == cart || cart.getCartItems().size() == 0) {
			throw new InvalidCartException(cartId);
		}		
		return cart;
	}
	
	

}
