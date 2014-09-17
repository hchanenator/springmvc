package com.packt.webstore.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.domain.Product;

@Controller
public class ProductController {
	
	@RequestMapping("/products")
	public String list(Model model) {
		Product iPhone = new Product("P1234", "iPhone 5S", new BigDecimal(500));
		iPhone.setDescription("Apple iPhone 5S - simply, awesome.");
		iPhone.setCategory("Smart Phone");
		iPhone.setManfacturer("Apple");
		iPhone.setUnitsInStock(1000);
		
		model.addAttribute("product", iPhone);
		
		return "products";
	}

}
