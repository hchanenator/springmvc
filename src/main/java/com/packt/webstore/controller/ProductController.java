package com.packt.webstore.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.NoProductFoundException;
import com.packt.webstore.exception.ProductNotFoundException;
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
	 * Getting by Category Example URL:
	 * http://localhost:8080/webstore/products/smartphone
	 * 
	 * @param model
	 * @param productCategory
	 * @return
	 */
	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		List<Product> products = productService.getProductsByCategory(productCategory);
		
		if (null == products || products.isEmpty()) {
			throw new NoProductFoundException();
		}
		
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	/**
	 * Using a MatrixVariable Example URL:
	 * http://localhost:8080/webstore/products/filter/ByCriteria;brand=apple,
	 * dell;category=laptop
	 * 
	 * @param filterParams
	 * @param model
	 * @return
	 */
	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
			Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	/**
	 * Retrieving via HTTP GET Example URL:
	 * http://localhost:8080/webstore/products/product?id=P7888
	 * 
	 * @param productId
	 * @param model
	 * @return
	 */
	@RequestMapping("/product")
	public String getProductById(Model model, @RequestParam("id") String productId) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	/**
	 * Example URL: 
	 *   http://localhost:8080/webstore/products/pricefilter/price;low=150;high=400
	 *   
	 * @param model
	 * @param productCategory
	 * @param filterParams
	 * @return
	 */
	@RequestMapping("/pricefilter/{price}")
	public String filterProductsByPrice(Model model,
			@MatrixVariable(pathVar = "price") Map<String, List<String>> priceRange) {

		model.addAttribute("products", productService.getProductsByPriceFilter(priceRange));
		return "products";

	}

	/**
	 * Example URL:
	 *   http://localhost:8080/webstore/products/filter/tablet/price;low=200;high=400
	 * 
	 * @param model
	 * @param productCategory
	 * @param filterParams
	 * @return
	 */
	@RequestMapping("filter/{category}/{price}")
	public String filterProductsByCategoryAndPrice(Model model, @PathVariable("category") String productCategory,
			@MatrixVariable(pathVar = "price") Map<String, List<String>> priceRange) {

		List<Product> finalResults = new ArrayList<Product>();

		finalResults = productService.getProductsByCategory(productCategory);
		finalResults.retainAll(productService.getProductsByPriceFilter(priceRange));

		model.addAttribute("products", finalResults);
		return "products";

	}
	
	/**
	 * Example URL:
	 *   http://localhost:8080/webstore/products/laptop/price;low=200;high=8000?manufacturer=apple
	 * 
	 * @param model
	 * @param productCategory
	 * @param filterParams
	 * @return
	 */
	@RequestMapping("/{category}/{price}")
	public String filterProductsCategoryPriceManufacturer(Model model, @PathVariable("category") String productCategory,
			@MatrixVariable(pathVar = "price") Map<String, List<String>> priceRange,
			@RequestParam("manufacturer") String manufacturer) {

		List<Product> finalResults = new ArrayList<Product>();

		finalResults = productService.getProductsByCategory(productCategory);
		finalResults.retainAll(productService.getProductsByPriceFilter(priceRange));
		finalResults.retainAll(productService.getProductsByManufacturer(manufacturer));

		model.addAttribute("products", finalResults);
		return "products";

	}

	/**
	 * Example URL:
	 *   http://localhost:8080/webstore/products/add
	 * 
	 * Called when a GET is requested
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}
	
	/**
	 * Example URL:
	 *   http://localhost:8080/webstore/products/add
	 * 
	 * Called when a POST is requested
	 * 
	 * @param newProduct
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result, HttpServletRequest request) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile productImage = newProduct.getProductImage();
		MultipartFile productManual = newProduct.getProductManual();
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".jpg"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		
		if (productManual != null && !productManual.isEmpty()) {
			try {
				productManual.transferTo(new File(rootDirectory + "resources\\pdf\\" + newProduct.getProductId() + ".pdf"));
			} catch (Exception e) {
				throw new RuntimeException("Product Manual saving failed", e);
			}
		}
				
		
		productService.addProduct(newProduct);
		return "redirect:/products";
	}
	
	/**
	 * For some security.  Defines which fields can be bound (whitelist), and which
	 * ones cannot.
	 * @param binder
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setDisallowedFields("unitsOnOrder", "discontinued");
		binder.setAllowedFields("productId","name","unitPrice","description","manufacturer","category","unitsInStock", "condition", "productImage", "productManual");
	}
	
	/**
	 * Exception handler
	 * @param request
	 * @param pnfe
	 * @return
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, ProductNotFoundException pnfe) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", pnfe.getProductId());
		mav.addObject("exception", pnfe);
		mav.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}

}
