package com.packt.webstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.webstore.service.api.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		
		return "products";
	}
	
	@RequestMapping("/all")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	/**
	 * Getting by Category
	 * @param model
	 * @param productCategory
	 * @return
	 */
	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}
	
	/**
	 * Using a MatrixVariable 
	 * @param filterParams
	 * @param model
	 * @return
	 */
	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String, List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	/**
	 * Retrieving via HTTP GET
	 * @param productId
	 * @param model
	 * @return
	 */
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}
	
	/**
	 * Example URL:
	 * 	http://localhost:8080/webstore/products/tablet/price;low=200;high=400?manufacturer=Google
	 * 
	 * @param model
	 * @param productCategory
	 * @param filterParams
	 * @return
	 */
	@RequestMapping("/{category}/{price}/")
	public String filterProducts(Model model, @PathVariable("category") String productCategory, 
			@MatrixVariable(pathVar = "price") Map<String, List<String>> price,
			@RequestParam("manufacturer") String manufacturer) {
		
		model.addAttribute("products", productService.getProductsByManufacturer(manufacturer));
		return "products";
		
	}

	

}
