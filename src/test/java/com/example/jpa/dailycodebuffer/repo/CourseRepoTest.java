package com.example.jpa.dailycodebuffer.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.derby.iapi.store.raw.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.jpa.dailycodebuffer.model.Course;
import com.example.jpa.dailycodebuffer.model.CourseMaterial;
import com.example.jpa.dailycodebuffer.model.Student;
import com.example.jpa.dailycodebuffer.model.Teacher;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CourseRepoTest {

	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private CourseMaterialRepo courseMaterialRepo;
	
	@Test
	@Order(1)
	void setup() {
		Course course = Course.builder()
				.courseTitle("DSA")
				.credit(6)
				.build();
		CourseMaterial courseMat = CourseMaterial.builder()
				.url("www.google.com")
				.course(course)
				.build();
		courseMaterialRepo.save(courseMat);
		
		Course course1 = Course.builder()
				.courseTitle("DBA")
				.credit(4)
				.build();
		CourseMaterial courseMat1 = CourseMaterial.builder()
				.url("www.google.com")
				.course(course1)
				.build();
		courseMaterialRepo.save(courseMat1);
		
		Course course2 = Course.builder()
				.courseTitle("Arc")
				.credit(7)
				.build();
		CourseMaterial courseMat2 = CourseMaterial.builder()
				.url("www.google.com")
				.course(course2)
				.build();
		courseMaterialRepo.save(courseMat2);
	}
	
	@Test
	@Order(3)
	void printCourses() {
		List<Course> courses = courseRepo.findAll();
		System.out.println(courses);
	}
	
	@Test
	@Order(2)
	void saveCourseWithTeacherObject() {
		Teacher teacher = Teacher.builder()
				.firstName("Priyanka")
				.lastName("Singh")
				.build();
		
		Course course = Course.builder()
				.courseTitle("Python")
				.credit(4)
				.teacher(teacher)
				.build();
		courseRepo.save(course);
	}
	
	@Test
	@Order(3)
	void findAllPagination() {
		Pageable firstPageWithThressRecords =
				PageRequest.of(0, 3);
		Pageable secondPageWithTwoRecords = 
				PageRequest.of(1, 2);
		
		List<Course> courses = courseRepo.findAll(firstPageWithThressRecords).getContent();
		
		Long totalElements = courseRepo.findAll(firstPageWithThressRecords).getTotalElements();
		int totalPages = courseRepo.findAll(firstPageWithThressRecords).getTotalPages();
		System.out.println("totalElements:"+totalElements+" totalPages:"+totalPages);

		System.out.println("Courses : "+courses);
	}

	@Test
	@Order(4)
	public void findAllWithSorting() {
		Pageable sortbyTitle = PageRequest.of(0, 2, Sort.by("courseTitle"));
		Pageable sortDescByCredit = PageRequest.of(0, 2, Sort.by("credit").descending());
		Pageable sortByCreditAndTitleDesc = PageRequest.of(0, 2, Sort.by("courseTitle").descending().and(Sort.by("credit")));
		
		List<Course> coursesSortByTitle = courseRepo.findAll(sortbyTitle).getContent();
		System.out.println("coursesSortByTitle: "+coursesSortByTitle);
		
		List<Course> coursessortDescByCredit = courseRepo.findAll(sortDescByCredit).getContent();
		System.out.println("coursessortDescByCredit: "+coursessortDescByCredit);
		
		List<Course> coursessortByCreditAndTitleDesc = courseRepo.findAll(sortByCreditAndTitleDesc).getContent();
		System.out.println("coursessortByCreditAndTitleDesc: "+coursessortByCreditAndTitleDesc);
	}
	
	@Test
	@Order(5)
	public void printFindByCourseTitleContaining() {
		List<Course> courses = courseRepo.findByCourseTitleContaining("D", PageRequest.of(0, 2)).getContent();
		System.out.println("Courses custom sort :"+courses);
	}
	
	@Test
	@Order(6)
	public void saveCourseWithStudentAndTeacher() {
		Teacher teacher = Teacher.builder()
				.firstName("Abhay")
				.lastName("Singla")
				.build();
		
		Course course = Course.builder()
				.courseTitle("Java")
				.credit(5)
				.teacher(teacher)
				.build();
		Student student = Student.builder()
				.firstName("Abhishek")
				.lastName("Singh")
				.emalilId("Abhishek!gmail.com")
				.build();
		course.addStudents(student);
		
		courseRepo.save(course);
	}
}
