package com.jdbc.StuService;

import java.util.List;

import com.jdbc.CustomExceptions.RecordNotFoundException;
import com.jdbc.Entity.Student;

public interface StuServiceInter {
	
	
	
	public Student save(Student student);
	
	
	  public void deleteById(int id) throws RecordNotFoundException;
	  
	  public List<Student>getAll();
	  
	  public Student findById(int id) throws RecordNotFoundException;
	  
	  public Student update(Student student,int id) throws RecordNotFoundException;



}
