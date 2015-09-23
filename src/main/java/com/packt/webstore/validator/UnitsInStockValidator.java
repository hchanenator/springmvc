/**
 * 
 */
package com.packt.webstore.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

/**
 * @author herb
 * 
 * Can't add more than 99 units of stock with unit cost > 1000
 *
 */
@Component
public class UnitsInStockValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Product.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		
		if (product.getUnitPrice() != null && new BigDecimal(1000).compareTo(product.getUnitPrice()) <= 0 && product.getUnitsInStock() > 99) {
			errors.rejectValue("unitsInStock","com.packt.webstore.validator.UnitsInStockValidator.message");
		}

	}

}
