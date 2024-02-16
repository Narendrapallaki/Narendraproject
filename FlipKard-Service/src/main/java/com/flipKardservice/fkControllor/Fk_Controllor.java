package com.flipKardservice.fkControllor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipKardservice.entity.Product_FK;
import com.flipKardservice.fkRepositoty.FkRepositoty;

@RestController
@RequestMapping("/flipKard")
public class Fk_Controllor {
	
	@Autowired
	private FkRepositoty fkRepositoty;
	@PostMapping("/saveProduct")
	public String saveProduct()
	{
		
		    
		fkRepositoty.saveAll(Stream.of(
				
				new Product_FK(1, "book", 1,200 ),
				new Product_FK(2, "bag", 3,1200),
				new Product_FK(3, "pen", 1,150 ),
				new Product_FK(4, "cap", 4,300 )
				).collect(Collectors.toList()));
		
		return "Your order is taken by flipKard";
	}

	
	@GetMapping("/getAll")
	public List<Product_FK>getAllProduct()
	{
		
		List<Product_FK> all = fkRepositoty.findAll();
		return all;
	}
	
	@GetMapping("/get/{name}")
	public List<Product_FK>getProduct(@PathVariable("name") String productName)
	{
		
		List<Product_FK> getByProduct = fkRepositoty.findByProductName(productName);
		return getByProduct;
	}
	
	
	
}
