package com.diego.microdemo.products.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diego.microdemo.products.model.Product;

/**
 * @author Diego Chavez
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product,String>  {

}
