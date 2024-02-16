package com.StoreProcedure.spController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.StoreProcedure.entity.Apple;
import com.StoreProcedure.spService.SpService;

@RestController
public class SpController {

	@Autowired
	private SpService spService;
	
	  Map<String,Object> response=new HashMap<>();
	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> save(@RequestBody Apple apple)
	{
		System.out.println("Controller space-----------"+apple);
	
           Map<String,Object> saveData = spService.saveData(apple);
           

        //<String,Object> response=new HashMap<>();
           
           response.put("Result :", saveData);
           response.put("Stratus :", HttpStatus.OK);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/getByID/{id}")
	public ResponseEntity<Map<String, Object>> readData(@PathVariable int id)
	{
		System.out.println("Controller space-----------"+id);
	
           Map<String,Object> getResult = spService.getById(id);
           
       //    Map<String,Object> response=new HashMap<>();
           
           response.put("Result", getResult);
           response.put("Stratus", HttpStatus.OK);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<Map<String, Object>> getAllFromTable()
	{
		System.out.println("Controller space-----------get ALL");
	
           Map<String,Object> getResult = spService.getAll();
           
      //    Map<String,Object> response=new HashMap<>();
           
           response.put("Result", getResult);
           response.put("Stratus", HttpStatus.OK);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Map<String, Object>> deleteByID(@PathVariable int id)
	{
		System.out.println("Controller space-----------delete");
	
            Map<String,Object> deleteByid = spService.deleteByid(id);
           
     //     Map<String,Object> response=new HashMap<>();
           
           response.put("Result", deleteByid);
           response.put("Stratus", HttpStatus.OK);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, Object>>update(@RequestBody Apple apple,@PathVariable int id)
	{
		System.out.println("Controller space update method -----------"+apple);
	
           Map<String,Object> updateData = spService.updateData(apple, id);
          
     //      Map<String,Object> response=new HashMap<>();
           
           response.put("Result :", updateData);
           response.put("Stratus :", HttpStatus.OK);
		return ResponseEntity.ok(response);
	}
	
	
}
