package com.example.jpa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	
	private String projectName;
	private LocalDate projectDeadline;
	
	@ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY )
	Set<Employee> employees;

	public Project(String projectName, LocalDate projectDeadline) {
		super();
		this.projectName = projectName;
		this.projectDeadline = projectDeadline;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public LocalDate getProjectDeadline() {
		return projectDeadline;
	}

	public void setProjectDeadline(LocalDate projectDeadline) {
		this.projectDeadline = projectDeadline;
	}

	

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDeadline="
				+ projectDeadline + "]";
	}
	
	
}
