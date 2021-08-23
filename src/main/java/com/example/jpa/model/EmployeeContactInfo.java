package com.example.jpa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class EmployeeContactInfo {

	@Id
	private Integer id;
	private String address;
	private String phoneNum;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId //both employee and contact info would share a common primary key. id and employee would combone and refer to employee entity
	private Employee employee;

	@Override
	public String toString() {
		return "EmployeeContactInfo [id=" + id + ", address=" + address + ", phoneNum=" + phoneNum + "]";
	}

	public EmployeeContactInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeContactInfo(String address, String phoneNum) {
		super();
		this.address = address;
		this.phoneNum = phoneNum;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
