package com.pagination.productController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.pagination.Entity.Product;
import com.pagination.productService.ProductService;

@RestController
public class ProductController {

	
	public final ProductService productService;
	

	public ProductController(ProductService productService) {
		
		this.productService = productService;
	}



	@PostMapping(value="/saveProduct")
	   public ResponseEntity<Map<Object, Object>> saveProductContro(@RequestBody Product product) {
	        Map<Object, Object> response = new HashMap<>();

	        Product savedProduct = productService.saveProduct(product);
         
	    //   response.put("Product data saved", savedProduct.getId());
	        response.put("Result", savedProduct);
System.out.println("Controller save method -----------------"+HttpStatus.OK);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	
	
	
	@GetMapping("/getAllProduct")
	public List<Product> getAllProduct() {

		Map<Object, Object> response = new HashMap<>();

	List<Product> all = productService.getAll();

		//response.put("product count:", all.size());
		//response.put("Result :",all);
	
		return all;
	}
	
	
	@GetMapping("/sortDetailsByField/{field}")
	public ResponseEntity<Map<Object, Object>> getAllProductSortByField(@PathVariable String field) {

		Map<Object, Object> response = new HashMap<>();

	List<Product> all = productService.sortProductByfield(field);
		response.put("product count:", all.size());
		response.put("Result :",all);
		return new ResponseEntity<Map<Object, Object>>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/getPage/{offset}/{pagesize}")
	public ResponseEntity<Map<String, Object>> getProductWithPagination(@PathVariable int offset, @PathVariable int pagesize) {
	    Map<String, Object> response = new HashMap<>();

	    Page<Product> productWithPagination = productService.findProductWithPagination(offset, pagesize);

	    // Extract information from the Page object
	    int productCount = productWithPagination.getNumberOfElements();
	    List<Product> products = productWithPagination.getContent();

	    //  the response
	    response.put("productCount", productCount);
	    response.put("products", products);
	    response.put("currentPage", productWithPagination.getNumber() + 1); // Page numbers start from 0
	    response.put("totalPages", productWithPagination.getTotalPages());
	    response.put("totalElements", productWithPagination.getTotalElements());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
