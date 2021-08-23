package com.example.jpa.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
