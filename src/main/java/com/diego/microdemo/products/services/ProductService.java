package com.diego.microdemo.products.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.microdemo.products.model.Product;
import com.diego.microdemo.products.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository prodRepository;

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
