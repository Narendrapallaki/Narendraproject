package com.springBatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
	
	
	@Id
	public Integer id;
	public String name;
	public String dept;
	public Integer salary;

}
