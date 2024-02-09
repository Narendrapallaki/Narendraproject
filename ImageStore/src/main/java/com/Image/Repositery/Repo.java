package com.Image.Repositery;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Image.Entity.Image;

@Repository
public interface Repo extends CrudRepository<Image, Long>
{
     public List<Image>findByimageName(String imageName);
}
