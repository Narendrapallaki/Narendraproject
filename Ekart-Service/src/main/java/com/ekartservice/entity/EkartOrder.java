package com.ekartservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EkartOrder {

	
	private int id;
	private String productName;
	private double quality;
	private double price;
	
}
