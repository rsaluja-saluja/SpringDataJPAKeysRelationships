package com.example.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name="employeedetails") //used to map this entity to mentioned table(employeedetails) in DBase
//@Table(name="EmployeeDetailsDemo") //map to table employee_details_demo
public class Employee {

	// @Id can be applied to getter of field
	@Id 
	@GeneratedValue // create and use that sequence to fill its value
	private Integer id;
	private String firstName;
	private String lastName;
	private String job;
	private Double salary;
	
	// mappedBy - var in other side of relationship
	// cascade - all operations will be propagated from parent to child
	// optional - contactInfo is optional means employee can be instantiated wout contactinfo
	@OneToOne(mappedBy = "employee",fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, optional = true)
	private EmployeeContactInfo contactInfo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "departmentId", nullable = false) //departmentId is foreign key pointing to primary key of department
	private Department department;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "EmployeesProjects",
				joinColumns = {
						@JoinColumn(name="employeeId", referencedColumnName = "id", nullable = false)},
				inverseJoinColumns = {
						@JoinColumn(name="projectid", referencedColumnName = "projectId", nullable = false)})
	private Set<Project> projects = new HashSet<>();
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String firstName, String lastName, String job, Double salary) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.job = job;
	this.salary = salary;
}

	//@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	public EmployeeContactInfo getContactInfo() {
		return contactInfo;
	}

	//Required to set bidirectional relationship between both entities
	public void setContactInfo(EmployeeContactInfo contactInfo) {
		if(contactInfo == null) {
			if(this.contactInfo != null) {
				this.contactInfo.setEmployee(null);
			}
		} else {
			contactInfo.setEmployee(this);
		}
		
		this.contactInfo = contactInfo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	public Set<Project> getProjects() {
		return projects;
	}

	public void addProjects(Project project) {
		this.projects.add(project);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", job=" + job
				+ ", salary=" + salary + ", contactInfo=" + contactInfo + "]";
	}

	
	
	
}
