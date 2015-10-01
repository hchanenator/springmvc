/**
 * 
 */
package com.packt.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author herb
 *
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4045729241960416615L;
	
	private String cartId;
	private Map<String, CartItem> cartItems;
	private BigDecimal grandTotal;

	/**
	 * 
	 */
	public Cart() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = new BigDecimal(0);
	}

	public Cart(String cartId) {
		this();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void addCartItem(CartItem cartItem) {
		String productId = cartItem.getProduct().getProductId();

		if (cartItems.containsKey(productId)) {
			CartItem existingCartItem = cartItems.get(productId);
			existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
			cartItems.put(productId, existingCartItem);
		} else {
			cartItems.put(productId, cartItem);
		}

		updateGrandTotal();
	}
	
	public void removeCartItem(CartItem cartItem) {
		String productId = cartItem.getProduct().getProductId();
		cartItems.remove(productId);
		updateGrandTotal();
	}

	public void updateGrandTotal() {
		for (CartItem cartItem : cartItems.values()) {
			grandTotal = grandTotal.add(cartItem.getTotalPrice());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((cartItems == null) ? 0 : cartItems.hashCode());
		result = prime * result + ((grandTotal == null) ? 0 : grandTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (cartItems == null) {
			if (other.cartItems != null)
				return false;
		} else if (!cartItems.equals(other.cartItems))
			return false;
		if (grandTotal == null) {
			if (other.grandTotal != null)
				return false;
		} else if (!grandTotal.equals(other.grandTotal))
			return false;
		return true;
	}
	
	

}
