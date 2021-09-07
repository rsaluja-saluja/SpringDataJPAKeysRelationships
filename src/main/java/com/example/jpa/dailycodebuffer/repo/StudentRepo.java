package com.example.jpa.dailycodebuffer.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jpa.dailycodebuffer.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

	public List<Student> findByFirstName(String fName);

	public List<Student> findByFirstNameContaining(String name);

	public List<Student> findByLastNameNotNull();

	public List<Student> findByGuardianName(String guardinName);

	// JPQL
	@Query("SELECT s from Student s where s.emalilId = ?1")
	public Student getStudentByEmailAddress(String emailId);

	// Native
	@Query(value = "SELECT * from tbl_student s where s.email_address = ?1", nativeQuery = true)
	public Student getStudentByEmailAddressUsingNative(String emailId);

	@Query("SELECT s.firstName from Student s where s.emalilId = ?1")
	public String getStudentFirstNameByEmailAddress(String emailId);

	// NativeNamedParameter
	@Query(value = "SELECT * from tbl_student s where s.email_address = :email", nativeQuery = true)
	public Student getStudentByEmailAddressUsingNativeNamedParam(@Param("email") String emailId);

	@Modifying
	@Transactional
	@Query(
			value = "update tbl_student set first_name = ?1 where email_address = ?2",
			nativeQuery = true)
	public int updateStudentNameByEmailId(String fName, String emailId);
}
