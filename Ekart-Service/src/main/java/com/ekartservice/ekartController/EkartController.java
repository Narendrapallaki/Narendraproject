package com.ekartservice.ekartController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ekartservice.entity.EkartOrder;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
@RestController
@RequestMapping("/ekart")
public class EkartController {

	
	private RestTemplate restTemplate;
	
	
	
	public EkartController(RestTemplate restTemplate) {
	//	super();
		this.restTemplate = restTemplate;
	}

	private int attemt=1;
	
	private static final String ekart_Service="ekartService";
	private static final String url="http://localhost:2020/flipKard";
	@GetMapping("/deliviry")
	//@CircuitBreaker(name=ekart_Service,fallbackMethod = "getFallBackMethod")
	@Retry(name=ekart_Service,fallbackMethod = "getFallBackMethod")
	public List<EkartOrder> get(@RequestParam("name") String productName)
	{
		 // String endPoint=productName==null?url+"/getAll":url+"/get/"+productName;
		
		String endPoint;
	    if (productName == null) {
	        endPoint = url + "/getAll";
	    } else {
	        endPoint = url + "/get/" + productName;
	    }
	    System.out.println("------------------" + endPoint);
        System.out.println("retry method called "+attemt++ +" times "+" at "+new Date());


		  EkartOrder[] forObject = restTemplate.getForObject(endPoint, EkartOrder[].class);
	
		 
		List<EkartOrder>response=new ArrayList<>(Arrays.asList(forObject));
   // System.out.println(response.toString()+"**********");
		//System.out.println("-------"+attemt+new Date());
		return response;
		
	}
	
	public List<EkartOrder> getFallBackMethod(String productName, Throwable throwable) {
	    return Arrays.asList(
	            new EkartOrder(1, "Fallback Product 1", 1, 100)
	         //   new EkartOrder(2, "Fallback Product 2", 1, 200)
	    );
	}
	
	   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
