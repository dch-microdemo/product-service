package com.diego.microdemo.products.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping(path = "/")
	public Iterable<Product> getProducts() {
		return prodService.findAll();
	}

	@GetMapping(path = "/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
		logger.debug("Looking up data for prod: {}", productId);

		Optional<Product> org = prodService.getProduct(productId);
		if (org.isPresent()) {
			return new ResponseEntity<>(org.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	@PutMapping(path = "/{productId}", consumes = "application/json")
	public Product updateProduct(@PathVariable("productId") Long prodId, @RequestBody Product prod) {
		prod.setId(prodId);
		return prodService.updateProduct(prod);

	}

	@PostMapping(path = "/", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Product saveProduct(@RequestBody Product prod) {
		return prodService.saveProduct(prod);
	}

	@DeleteMapping(path = "/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("productId") Long prodId) {
		prodService.deleteProduct(prodId);

	}
}
