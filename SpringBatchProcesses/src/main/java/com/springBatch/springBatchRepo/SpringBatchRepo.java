package com.springBatch.springBatchRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBatch.entity.Users;
@Repository
public interface SpringBatchRepo extends JpaRepository<Users, Integer> {

}
