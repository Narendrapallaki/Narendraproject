package com.pagination.productService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pagination.Entity.Product;
import com.pagination.productRepositery.ProductRepositery;

@Service
public class ProductService {

	@Autowired
	private ProductRepositery productRepositery;
    
	// This we can use to save the data into the data base
	public Product saveProduct(Product product) {

		return productRepositery.save(product);
	}

	//This method will perform to get all data from data base
	 public List<Product> getAll()
	 {
		return (List<Product>) productRepositery.findAll();
	 }
	 
	 //This method we can sort the product by any field
	 public List<Product>sortProductByfield(String field)
	 {
		 return productRepositery.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC,field));
		 
	 }
	 
	 public org.springframework.data.domain.Page<Product>findProductWithPagination(int offSet,int pageSize)
	 {
		 org.springframework.data.domain.Page<Product> all = productRepositery.findAll(PageRequest.of(offSet, pageSize));
		 
		 return all; 
	 }
	 
}
