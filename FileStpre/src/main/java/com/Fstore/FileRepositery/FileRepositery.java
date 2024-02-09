package com.Fstore.FileRepositery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Fstore.FileEntity.FileEntity;

@Repository
public interface FileRepositery extends CrudRepository<FileEntity, Long>
{
          public FileEntity findByfileName(String fileName);
}
