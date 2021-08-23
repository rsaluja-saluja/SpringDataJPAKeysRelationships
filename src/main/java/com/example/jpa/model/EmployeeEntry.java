package com.example.jpa.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EmployeeEntry {

	@EmbeddedId
	EmployeeEntryId empEntryDate;
	LocalTime entryTime;
	
	public EmployeeEntry(Integer employeeId, LocalDate entryDate, LocalTime entryTime) {
		this.empEntryDate = new EmployeeEntryId(employeeId, entryDate);
		this.entryTime = entryTime;
	}

	public EmployeeEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeEntryId getEmpEntryDate() {
		return empEntryDate;
	}

	public void setEmpEntryDate(EmployeeEntryId empEntryDate) {
		this.empEntryDate = empEntryDate;
	}

	public LocalTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalTime entryTime) {
		this.entryTime = entryTime;
	}

	@Override
	public String toString() {
		return "EmployeeEntry [employeeId=" + empEntryDate.getEmployeeId() + 
								", entryDate=" + empEntryDate.getEntryDate() + 
								", entryTime=" + entryTime + "]";
	}
	
	
}
