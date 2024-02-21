package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.EmpRepo.EmployeRepo;
import com.employee.Entity.Employee;
import com.employee.Entity.ShiftTimes;
import com.employee.UserValidation.EmployeUser;

@Service
public interface SerInterface 
{
          
	      public Employee saveData(EmployeUser euser);
	      
	      public Iterable<Employee>getAllDetails();
	      
	      public String deleteById(Long id);
	      
	     // public  Optional<Employee> updateById(Long id,Employee emp);
	      
	     //public Employee updateSave(Employee employee);
	      
	      public String update(Employee employee,Long long1);
	      
	      public Optional<Employee> getById(Long long1);
	      
	// public Optional<Employee>getActiveEmploye(String sts, Long id);
	      
	    // public List<Employee>findByStatus();
	      
	      public Employee addShiftTimesByEmploye(Long empId,List<ShiftTimes> shiftTimes);
}
