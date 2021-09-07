package com.example.jpa.dailycodebuffer.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.dailycodebuffer.model.Course;
import com.example.jpa.dailycodebuffer.model.CourseMaterial;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CourseMaterialRepoTest {

	@Autowired
	private CourseMaterialRepo courseMaterialRepo;
	
	@Test
	@Order(1)
	public void addCourseMaterial() {
		Course course = Course.builder()
				.courseTitle("DSA")
				.credit(6)
				.build();
		CourseMaterial courseMat = CourseMaterial.builder()
				.url("www.google.com")
				.course(course)
				.build();
		courseMaterialRepo.save(courseMat);
		
	}
	@Test
	public void printCourseMaterial() {
		
		List<CourseMaterial> courseMaterials = courseMaterialRepo.findAll();
		System.out.println("courseMaterials: "+courseMaterials);
	}

}
