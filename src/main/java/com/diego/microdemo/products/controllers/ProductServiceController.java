package com.diego.microdemo.products.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diego.microdemo.products.model.Product;
import com.diego.microdemo.products.services.ProductService;

/**
 * @author Diego Chavez
 *
 */
@RestController
@RequestMapping(value = "products", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProductServiceController {

	private final ProductService prodService;

	/**
	 * @param prodService
	 */
	public ProductServiceController(ProductService prodService) {
		this.prodService = prodService;
	}

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Product> getProducts() {
		return prodService.findAll();
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
		logger.debug("Looking up data for prod: {}", productId);

		Optional<Product> org = prodService.getProduct(productId);
		if (org.isPresent()) {
			return new ResponseEntity<>(org.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/{productId}", consumes = "application/json", method = RequestMethod.PUT)
	public void updateProduct(@PathVariable("productId") String prodId, @RequestBody Product prod) {
		prodService.updateProduct(prod);

	}

	@RequestMapping(value = "/", consumes = "application/json", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void saveProduct(@RequestBody Product prod) {
		prodService.saveProduct(prod);
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("productId") String prodId) {
		prodService.deleteProduct(prodId);

	}
}
