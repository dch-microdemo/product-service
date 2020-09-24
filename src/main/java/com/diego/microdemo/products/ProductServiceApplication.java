package com.diego.microdemo.products;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author Diego Chavez
 *
 */
@SpringBootApplication
public class ProductServiceApplication {
	
	private int aversipasa;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	
    @PreDestroy
    public void destroy() {
        System.out.println(
          "Callback triggered - @PreDestroy.");
    }

}
