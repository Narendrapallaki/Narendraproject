package com.pagination.productControTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pagination.Entity.Product;
import com.pagination.productController.ProductController;
import com.pagination.productService.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControTest {

	@MockBean
	public ProductService productService;

	@Autowired
	public ProductController controller;
	
	@Autowired
	public MockMvc mockMvc;

	@Test
	public void saveDateTest() throws JsonProcessingException, Exception {
		Product requestProduct = new Product(1, "samleProduct", 100, 5);

		// Create a sample Product for the response
		Product savedProduct = new Product(1, "samleProduct", 100, 5);

		when(productService.saveProduct(requestProduct)).thenReturn(savedProduct);

		mockMvc.perform(
				post("/saveProduct").contentType(MediaType.APPLICATION_JSON).content(asJsonString(requestProduct)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.Result.id").value(savedProduct.getId()))
				.andExpect(jsonPath("$.Result.name").value(savedProduct.getName()))
				.andExpect(jsonPath("$.Result.amount").value(savedProduct.getAmount()))
				.andExpect(jsonPath("$.Result.quality").value(savedProduct.getQuality()));
		System.out.println("--------------------" + savedProduct.getName());

		Assertions.assertEquals(requestProduct, savedProduct);
	}

	private static String asJsonString(Object object) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
    public void getAllProductTest() throws Exception {
        // Prepare mock data
        List<Product> mockProducts = Arrays.asList(
                new Product(1, "bot", 4, 2),
                new Product(2, "bott", 45, 1)
        );
    System.out.println("**********"+mockProducts);
        // Mocking productService
        Mockito.when(productService.getAll()).thenReturn(mockProducts);

        // Perform the request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllProduct")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    //    ResponseEntity<Map<Object,Object>> allProduct = controller.getAllProduct();
                  List<Product> allProduct = controller.getAllProduct();
        
//        Map<Object,Object> body = allProduct.getBody();
        
        System.out.println("-----------"+allProduct);
       Assertions.assertEquals(mockProducts,allProduct);
             
              
    }
	
	

}
