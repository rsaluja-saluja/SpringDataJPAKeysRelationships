package com.example.jpa.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(EmployeeEntryId.class)
public class EmployeeEntryCKWay2 {

	@Id
	private Integer employeeId;
	
	@Id
	private LocalDate entryDate;
	
	private LocalTime entryTime;

	public EmployeeEntryCKWay2(Integer employeeId, LocalDate entryDate, LocalTime entryTime) {
		super();
		this.employeeId = employeeId;
		this.entryDate = entryDate;
		this.entryTime = entryTime;
	}

	public EmployeeEntryCKWay2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public LocalTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalTime entryTime) {
		this.entryTime = entryTime;
	}

	@Override
	public String toString() {
		return "EmployeeEntryCKWay2 [employeeId=" + employeeId + ", entryDate=" + entryDate + ", entryTime="
				+ entryTime + "]";
	}
	
	
}
