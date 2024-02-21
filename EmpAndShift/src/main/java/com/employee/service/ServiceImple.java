package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.EmpRepo.EmployeRepo;
import com.employee.EmpRepo.ShifRepo;
import com.employee.Entity.Employee;
import com.employee.Entity.ShiftTimes;
import com.employee.UserValidation.EmployeUser;

@Service
public class ServiceImple implements SerInterface
{
	
     @Autowired      
	 public EmployeRepo employeRepo;
     
     @Autowired
     public ShifRepo repo;

	@Override
	public Employee saveData(EmployeUser euser) {
		// TODO Auto-generated method stub
		
		Employee e1=new Employee(euser.getEmpId(), euser.getName(), euser.getEmail(), euser.getDateJoin(),
				euser.getMobile(), euser.getStatus(), "isActive".equals(euser.getStatus())?true:false,
						euser.getAbout(),euser.getShiftTimings());
	//	System.out.println(e1);
	//	new ShiftTimes().setEmpId(euser.getId());
		return employeRepo.save(e1);
	
	}

	@Override
	public Iterable<Employee> getAllDetails() {
		// TODO Auto-generated method stub
		return employeRepo.findAll();
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		
		employeRepo.deleteById(id);
		return "Data deleted in database";
	}

	@Override
	public String update(Employee employee, Long long1) {
		// TODO Auto-generated method stub
		
		Employee emp = employeRepo.findById(long1).get();
		
	      emp.setAbout(employee.getAbout());
	      
	      emp.setDateJoin(employee.getDateJoin());
	      
	      emp.setEmail(employee.getEmail());
	      
	  //    emp.setDeleted(employee.get);
	      emp.setDeleted(employee.isDeleted());
	      emp.setMobile(employee.getMobile());
	      emp.setName(employee.getName());
	      
	      emp.setStatus(employee.getStatus());
	      emp.setShiftTimings(employee.getShiftTimings());   
	  
	      employeRepo.save(emp);
		return "Data updated";
	}

	@Override
	public Optional<Employee> getById(Long long1) {
		// TODO Auto-generated method stub
		 Optional<Employee> findById = employeRepo.findById(long1);
		return findById;
		
	}

	/*@Override
	public Optional<Employee> getActiveEmploye(String sts, Long id) {
		// TODO Auto-generated method stub
		
		   
		  Optional<Employee> optional = employeRepo.getActiveEmploye(sts, id);
		return optional;
	}

	*/
	
	
	public List<Employee>findByStatusEmp(String sts)
	{
		return employeRepo.findByStatus(sts);
		
		
	}
	

	

	public Optional<ShiftTimes> findByShiftTimeId(long shiftTimeId)
	{
		return repo.findById(shiftTimeId);
		
	}
	
	

	@Override
	public Employee addShiftTimesByEmploye(Long empId, List<ShiftTimes> shiftTimes) 
	{
		// TODO Auto-generated method stub
		Optional<Employee> byId = getById(empId);
		
		List<ShiftTimes>shiftTimes2=new ArrayList<>();
		
		
		return null;
	}


}
