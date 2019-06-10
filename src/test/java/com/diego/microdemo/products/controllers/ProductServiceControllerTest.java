package com.diego.microdemo.products.controllers;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.diego.microdemo.products.model.Product;
import com.diego.microdemo.products.repository.ProductRepository;
import com.diego.microdemo.products.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductService prodService;
	
	@Autowired
	private ProductRepository prodRepo;
    
	@Before
	public void setUp() {
		prodRepo.deleteAll();
	}
    
	@Test
	public void getProductsInitial() throws Exception {
		mockMvc.perform(get("/products/")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().json("[]"));
	}

	@Test
	public void getProductsAfterSavingCouple() throws Exception {
		Product prod = new Product();
		prod.setName("Diego");
		prod.setContactEmail("diego@gmail.com");
		prod.setContactName("Diego Chavez");
		Product prodGuardado= prodService.saveProduct(prod);

		Product prod2 = new Product();
		prod2.setName("Diego2");
		prod2.setContactEmail("diego2@gmail.com");
		prod2.setContactName("Diego2 Chavez");
		Product prodGuardado2 = prodService.saveProduct(prod2);

		mockMvc.perform(get("/products/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(prodGuardado.getId().intValue())))
				.andExpect(jsonPath("$[0].name", is("Diego")))
				.andExpect(jsonPath("$[1].id", is(prodGuardado2.getId().intValue())))
				.andExpect(jsonPath("$[1].name", is("Diego2")));
	}
	
	@Test
	public void getProductAfterSavingOne() throws Exception {
		Product prod = new Product();
		prod.setName("Producto1");
		prod.setContactEmail("diego@gmail.com");
		prod.setContactName("Diego Chavez");
		Product prodGuardado= prodService.saveProduct(prod);

		MvcResult mvcResult = mockMvc.perform((get("/products/{id}", prodGuardado.getId())))
		.andExpect(status().isOk())
		.andReturn();
		Product productoRecibido = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);		
		assertThat(prodGuardado.getId()).isEqualTo(productoRecibido.getId());
		assertThat(prodGuardado.getName()).isEqualTo(productoRecibido.getName());
		assertThat(prodGuardado.getContactName()).isEqualTo(productoRecibido.getContactName());
		assertThat(prodGuardado.getContactEmail()).isEqualTo(productoRecibido.getContactEmail());
	}
	
	@Test
	public void getProductAfterSavingNone() throws Exception {

		mockMvc.perform((get("/products/{id}", 1)))
		.andExpect(status().isNotFound());

	}
	
	@Test
	public void deleteProductAfterSavingOne() throws Exception {

		Product prod = new Product();
		prod.setName("Producto1");
		prod.setContactEmail("diego@gmail.com");
		prod.setContactName("Diego Chavez");
		Product prodGuardado = prodService.saveProduct(prod);
		
		mockMvc.perform((delete("/products/{id}", prodGuardado.getId())))
		.andExpect(status().isNoContent());
		assertThat(prodService.getProduct(prodGuardado.getId()).equals(Optional.empty()));

	}
	
	@Test
	public void updateProductAfterSavingOne() throws Exception {

		Product prod = new Product();
		prod.setName("Producto1");
		prod.setContactEmail("diego@gmail.com");
		prod.setContactName("Diego Chavez");
		Product prodGuardado = prodService.saveProduct(prod);
		
		Product prodActualizar = new Product();
		prodActualizar.setId(prodGuardado.getId());
		prodActualizar.setName("Producto1");
		prodActualizar.setContactEmail("diego2@gmail.com");
		prodActualizar.setContactName("Diego Chavez2");
		
		
		mockMvc.perform((put("/products/{id}", prodGuardado.getId()))
		.contentType("application/json")
		.content(objectMapper.writeValueAsString(prodActualizar)))
		.andExpect(status().isOk());
		Product productoGuardado= prodService.getProduct(prodGuardado.getId()).get();
		
		assertThat((productoGuardado.getContactName())).isEqualTo(prodActualizar.getContactName());

	}
	
	@Test
	public void saveProductInitial() throws Exception {
		Product prod = new Product();
		prod.setName("Producto1");
		prod.setContactEmail("diego@gmail.com");
		prod.setContactName("Diego Chavez");
				
		MvcResult mvcResult = mockMvc.perform((post("/products/"))
		.contentType("application/json")
		.content(objectMapper.writeValueAsString(prod)))
		.andExpect(status().isCreated()).andReturn();
		Product productoRecibido = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);
		assertThat(productoRecibido.getName()).isEqualTo(prod.getName());
		Product productoGuardado= prodService.getProduct(productoRecibido.getId()).get();
		assertThat(productoGuardado.getName()).isEqualTo(prod.getName());
	}
	

}
