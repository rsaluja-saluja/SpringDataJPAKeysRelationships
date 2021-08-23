package com.example.jpa.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.model.Project;

public interface ProjectRepo extends CrudRepository<Project	, Integer>{

}
