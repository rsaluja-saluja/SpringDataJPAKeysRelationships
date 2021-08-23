package com.example.jpa.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
