/**
 * 
 */
package com.packt.webstore.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author herb
 *
 */
public class CategoryValidator implements ConstraintValidator<Category, String> {
	
	private final List<String> validCategories = new ArrayList<>();
	
	public CategoryValidator() {
		validCategories.add("SmartPhone");
		validCategories.add("Tablet");
		validCategories.add("Laptop");
	}

	@Override
	public void initialize(Category constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		for (String string : validCategories) {
			if (value.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;
	}

	
}
