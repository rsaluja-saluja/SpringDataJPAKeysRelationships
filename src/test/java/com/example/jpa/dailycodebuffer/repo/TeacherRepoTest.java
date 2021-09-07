package com.example.jpa.dailycodebuffer.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.dailycodebuffer.model.Course;
import com.example.jpa.dailycodebuffer.model.Teacher;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TeacherRepoTest {

	@Autowired
	private TeacherRepo teacherRepo;
	
	@Test
	void saveTecher() {
		Course course1 = Course.builder()
				.courseTitle("DSA")
				.credit(6)
				.build();
		Course course2 = Course.builder()
				.courseTitle("DBA")
				.credit(3)
				.build();
		
		Teacher teacher = Teacher.builder()
				.firstName("Teacher1")
				.lastName("Teacher1LastName")
				//.courses(Lists.list(course1,course2))
				.build();
		
		teacherRepo.save(teacher);
	}

}
