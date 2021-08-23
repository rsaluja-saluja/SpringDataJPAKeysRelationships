package com.example.jpa.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.model.EmployeeEntry;
import com.example.jpa.model.EmployeeEntryId;

public interface EmployeeEntryRepo extends CrudRepository<EmployeeEntry, EmployeeEntryId> {

}
