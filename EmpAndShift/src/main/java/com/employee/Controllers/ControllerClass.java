package com.employee.Controllers;

import java.util.List;
import java.util.Optional;

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


import com.employee.Entity.Employee;
import com.employee.Entity.ShiftTimes;
import com.employee.UserValidation.EmployeUser;
import com.employee.service.SerInterface;
import com.employee.service.ServiceImple;

import jakarta.validation.Valid;

@RestController
public class ControllerClass {
	
	
	
	  @Autowired
	  public SerInterface interface1;
	  
	 
	  
	  @PostMapping("/savetmp")
	  public ResponseEntity<Employee>saveEmploye(@Valid @RequestBody EmployeUser employeUser)
	  {
		return new ResponseEntity<Employee>(interface1.saveData(employeUser), HttpStatus.CREATED);
		  
	  }
    
	  
	  @GetMapping("/getAll")
	  public ResponseEntity<Iterable<Employee>> getAll()
	  {
		
		  Iterable<Employee> details = interface1.getAllDetails();
		    
		   
		  return new ResponseEntity<Iterable<Employee>>(details, HttpStatus.OK);
		  
	  }
	  
	  
	  
	   @DeleteMapping("/deleted/{id}")
	    public ResponseEntity<String>deleted(@PathVariable Long id)
	    {
	    	
			return new ResponseEntity<String>(interface1.deleteById(id), HttpStatus.OK);
	    	
	    }
	  
	   @PutMapping("/update/{id}")
	   public ResponseEntity<String>updateData(@Valid @PathVariable Long id,@RequestBody Employee employee)
	   {
		   
		   
		return new ResponseEntity<String>(interface1.update(employee, id), HttpStatus.OK);
		   
	   }
	  
	  
	  
	   @GetMapping("/getbyid/{long1}")
	   public ResponseEntity<Optional<Employee>>getByid(@PathVariable Long long1)
	   {
		return new ResponseEntity<Optional<Employee>>(interface1.getById(long1), HttpStatus.OK);
		   
	   }

	   @Autowired
	   public ServiceImple asd;
	
	   
	   @GetMapping("/getStatus/{status}")
	   public List<Employee>getByStatus(@PathVariable String status)
	   {
		return asd.findByStatusEmp(status);
		   
	   }
	
	
	   @GetMapping("/getShiFitId/{long1}")
	   public Optional<ShiftTimes>getSId(@PathVariable Long long1)
	   {
		return asd.findByShiftTimeId(long1);
		   
	   }

}
