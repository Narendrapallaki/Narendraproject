package com.batch_processing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

	@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;

	private String title;
	private String description;
	private String price;
	private String discount;
	private String discounted_price;
}
