package com.pagination.productRepositery;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagination.Entity.Product;
@Repository
public interface ProductRepositery extends JpaRepository<Product, Integer>
{


}
