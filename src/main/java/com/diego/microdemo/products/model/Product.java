package com.diego.microdemo.products.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Diego Chavez
 *
 */
@Data
@Entity
@Table(name = "products")
public class Product {
	@Id
	@Column(name = "product_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "name", nullable = false)
	String name;

	@Column(name = "contact_name", nullable = false)
	String contactName;

	@Column(name = "contact_email", nullable = false)
	String contactEmail;

}
