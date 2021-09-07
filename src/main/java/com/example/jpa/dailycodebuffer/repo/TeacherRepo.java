package com.example.jpa.dailycodebuffer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.dailycodebuffer.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long>{

}
