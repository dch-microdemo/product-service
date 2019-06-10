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

	public Optional<Product> getProduct(Long productId) {
		return prodRepository.findById(productId);
	}

	public Product saveProduct(Product prod) {
		return prodRepository.save(prod);
	}

	public Product updateProduct(Product prod) {
		return prodRepository.save(prod);

	}

	public void deleteProduct(Long prodId) {
		prodRepository.deleteById(Long.valueOf(prodId));
	}
}
