package com.example.jpa.dailycodebuffer.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.dailycodebuffer.model.Guardian;
import com.example.jpa.dailycodebuffer.model.Student;

@SpringBootTest
//@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class StudentRepoTest {

	@Autowired
	private StudentRepo studentRepo;
	
	@Test
	@Order(1)
	void saveStudent() {
		Student s = Student.builder()
				.emalilId("student1@gmail.com")
				.firstName("student1")
				.lastName("Saxena")
				.build();
		studentRepo.save(s);
		Student s1 = Student.builder()
				.emalilId("student2@gmail.com")
				.firstName("student2")
				.lastName("Saxena")
				.build();
		studentRepo.save(s1);
		Student s2 = Student.builder()
				.emalilId("student3@gmail.com")
				.firstName("student3")
//				.lastName("Saxena")
				.build();
		studentRepo.save(s2);

		System.out.println(studentRepo.findAll());
	}
	@Test
	@Order(2)
	void saveStudentWithGuardianDetails() {
		Guardian guardian = Guardian.builder()
				.email("guardinalEmail@gmail.com")
				.name("Guardian1")
				.mobile("123456789")
				.build();
		Student s = Student.builder()
				.emalilId("student4@gmail.com")
				.firstName("student4")
				.lastName("Saxena")
				.guardian(guardian)
				.build();
		studentRepo.save(s);
		System.out.println(studentRepo.findAll());
	}
	@Test
	void printStudentByFName() {
		
		List<Student> students = studentRepo.findByFirstName("student2");
		System.out.println("Students List:"+students);
	}
	@Test
	void printStudentByFNameContaining() {
		
		List<Student> students = studentRepo.findByFirstNameContaining("student");
		System.out.println("Students List:"+students);
	}
	@Test
	void printStudentByLNameNotNull() {
		
		List<Student> students = studentRepo.findByLastNameNotNull();
		System.out.println("printStudentByLNameNotNull:"+students);
	}
	@Test
	void printStudentByGuardinName() {
		
		List<Student> students = studentRepo.findByGuardianName("Guardian1");
		System.out.println("printStudentByGuardinName:"+students);
	}
	@Test
	void printStudentByEmailId() {
		
		Student student = studentRepo.getStudentByEmailAddress("student2@gmail.com");
		System.out.println("printStudentByEmailId:"+student);
	}
	@Test
	void printStudentByEmailIdUsingNative() {
		
		Student student = studentRepo.getStudentByEmailAddressUsingNative("student2@gmail.com");
		System.out.println("printStudentByEmailIdUsingNative:"+student);
	}

	@Test
	void printStudentFirstNameByEmailId() {
		
		String student = studentRepo.getStudentFirstNameByEmailAddress("student2@gmail.com");
		System.out.println("printStudentFirstNameByEmailId:"+student);
	}
	
	@Test
	void printStudentByEmailIdUsingNativeNamedParam() {
		
		Student student = studentRepo.getStudentByEmailAddressUsingNativeNamedParam("student2@gmail.com");
		System.out.println("printStudentByEmailIdUsingNativeNamedParam:"+student);
	}
	
	@Test
	void updateStudentNameByEmailId() {
		
		int status = studentRepo.updateStudentNameByEmailId("student3Changed","student3@gmail.com");
		System.out.println("updateStudentNameByEmailId:"+studentRepo.findByFirstNameContaining("student3"));
	}
}

