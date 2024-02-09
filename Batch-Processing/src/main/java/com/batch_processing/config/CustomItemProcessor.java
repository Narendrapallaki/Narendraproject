package com.batch_processing.config;

import org.springframework.batch.item.ItemProcessor;

import com.batch_processing.entity.Product;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {

	@Override
	public Product process(Product item) throws Exception {
		// here will write the logic of transform

		System.out.println("Discount Value: " + item.getDiscount());
		try {
			int discountPer = Integer.parseInt(item.getDiscount());
			double originalPrice = Double.parseDouble(item.getPrice());
			double discount = (discountPer * originalPrice) / 100;
			double finalPrice = originalPrice - discount;
			item.setDiscounted_price(String.valueOf(finalPrice));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return item;
	}

}
