package com.diego.microdemo.products.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diego.microdemo.products.model.Product;
import com.diego.microdemo.products.repository.ProductRepository;

/**
 * @author Diego Chavez
 *
 */
@Service
public class ProductService {
	/**
	 * @param prodRepository
	 */
	public ProductService(ProductRepository prodRepository) {
		this.prodRepository = prodRepository;
	}

	private final ProductRepository prodRepository;

	public Iterable<Product> findAll() {
		return prodRepository.findAll();
	}

	public Optional<Product> getProduct(String productId) {
		return prodRepository.findById(productId);
	}

	public void saveProduct(Product prod) {
		prodRepository.save(prod);
	}

	public void updateProduct(Product prod) {
		prodRepository.save(prod);

	}

	public void deleteProduct(String prodId) {
		prodRepository.deleteById(prodId);
	}
}
