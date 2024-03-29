package com.batch_processing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.batch_processing.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
