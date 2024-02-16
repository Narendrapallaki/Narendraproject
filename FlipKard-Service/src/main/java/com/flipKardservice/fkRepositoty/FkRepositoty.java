package com.flipKardservice.fkRepositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipKardservice.entity.Product_FK;
@Repository
public interface FkRepositoty extends JpaRepository<Product_FK, Integer>{

	
	public List<Product_FK>findByProductName(String productName);
}
