package com.example.Repositery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Student;

@Repository
public interface Repo extends CrudRepository<Student, Integer>{

	
	
	

}
