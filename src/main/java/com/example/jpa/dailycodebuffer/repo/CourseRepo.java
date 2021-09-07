package com.example.jpa.dailycodebuffer.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.dailycodebuffer.model.Course;

public interface CourseRepo extends JpaRepository<Course, Long>{

	Page<Course> findByCourseTitleContaining(String title, Pageable pageRequest);
}
