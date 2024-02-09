package com.example.Entity;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@Id
	public int id;
	public String name;
	public String mobile;

}









