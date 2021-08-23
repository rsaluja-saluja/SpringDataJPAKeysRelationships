package com.example.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import java.util.Objects;

@Embeddable
public class EmployeeEntryId implements Serializable {

	// both fields make composite key
	private Integer employeeId;
	private LocalDate entryDate;
	
	public EmployeeEntryId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeEntryId(Integer employeeId, LocalDate entryDate) {
		super();
		this.employeeId = employeeId;
		this.entryDate = entryDate;
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
	@Override
	public String toString() {
		return "EmployeeEntryId [employeeId=" + employeeId + ", entryDate=" + entryDate + "]";
	}
	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
//		result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
//		return result;
		//both hash methods are same
		return Objects.hash(employeeId,entryDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeEntryId other = (EmployeeEntryId) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (entryDate == null) {
			if (other.entryDate != null)
				return false;
		} else if (!entryDate.equals(other.entryDate))
			return false;
		return true;
	}
	
	
}
