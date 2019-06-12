package com.diego.microdemo.products.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.diego.microdemo.products.model.Product;
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepo;

	@Test
	public void testFindByName() {
		Product prod1 = new Product();
		prod1.setName("Producto1");
		prod1.setContactEmail("diego@gmail.com");
		prod1.setContactName("Diego Chavez");
        entityManager.persist(prod1);

        List<Product> findByName = productRepo.findByName(prod1.getName());

        assertThat(findByName).extracting(Product::getName).containsOnly(prod1.getName());
	}

}
