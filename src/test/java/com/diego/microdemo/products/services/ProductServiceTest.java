/**
 * 
 */
package com.diego.microdemo.products.services;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.diego.microdemo.products.model.Product;
import com.diego.microdemo.products.repository.ProductRepository;

/**
 * @author CHA14309
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	ProductRepository repo;

	@InjectMocks
	ProductService productService;

	@Test
	public void testFindAll() {

		Product prod1 = new Product();
		prod1.setName("Producto1");
		prod1.setId(1L);
		prod1.setContactEmail("diego@gmail.com");
		prod1.setContactName("Diego Chavez");

		Product prod2 = new Product();
		prod2.setId(2L);
		prod2.setName("Producto2");
		prod2.setContactEmail("diego2@gmail.com");
		prod2.setContactName("Diego Chavez2");

		Iterable<Product> productosMock = Arrays.asList(prod1, prod2);

		when(repo.findAll()).thenReturn(productosMock);

		Iterable<Product> productosDevueltos = productService.findAll();

		verify(repo, times(1)).findAll();
		verifyNoMoreInteractions(repo);
		assertThat(IterableUtils.size(productosDevueltos)).isEqualTo(2);
		assertTrue(CollectionUtils.isEqualCollection((Collection<Product>) productosMock,
				(Collection<Product>) productosDevueltos));
	}

	@Test
	public void testGetProduct() {
		Product prod1 = new Product();
		prod1.setName("Producto1");
		prod1.setId(1L);
		prod1.setContactEmail("diego@gmail.com");
		prod1.setContactName("Diego Chavez");
		Optional<Product> optProduct = Optional.of(prod1);
		when(repo.findById(1L)).thenReturn(optProduct);
		Product productDevuelto = productService.getProduct(1L).get();
		verify(repo, times(1)).findById(1L);
		verifyNoMoreInteractions(repo);
		assertThat(productDevuelto.getContactEmail()).isEqualTo(prod1.getContactEmail());
		
	}

}
