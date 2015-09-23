/**
 * 
 */
package com.packt.webstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.packt.webstore.domain.Product;

/**
 * @author herb
 *
 */
@Component
public class ProductImageValidator implements Validator {
	
	private Long allowedSize;
	
	public ProductImageValidator() {
		allowedSize = 10000l;
	}
	
	public void setAllowedSize(String allowedSize) {
		this.allowedSize = Long.parseLong(allowedSize);
	}

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

		MultipartFile imageFile = product.getProductImage();
		if (imageFile.getSize() > allowedSize) {
			errors.rejectValue("productImage", "com.packt.webstore.validator.ProductImageValidator.message");
		}
	}

}
