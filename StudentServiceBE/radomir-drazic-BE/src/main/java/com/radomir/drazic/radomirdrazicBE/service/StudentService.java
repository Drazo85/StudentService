package com.radomir.drazic.radomirdrazicBE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.radomir.drazic.radomirdrazicBE.dto.StudentDto;
import com.radomir.drazic.radomirdrazicBE.entity.Exam;
import com.radomir.drazic.radomirdrazicBE.entity.Student;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;

public interface StudentService {

	 	List<StudentDto> findAllStudents();
	    
	    Page<StudentDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

	    List<StudentDto> findOnlySomeStudents(int number);

	    Optional<StudentDto> findById(Long id) throws InvalidEntityException;

	    StudentDto addStudent(StudentDto student) throws EntityExistsException;

	    void deleteStudent(Long id) throws InvalidEntityException;

	    StudentDto editStudent(StudentDto student) throws InvalidEntityException;
	    
	    List<StudentDto> findStudentsByExamId(Long id) throws InvalidEntityException;

	    Student addActiveExamToStudent(Exam exam, Long id) throws InvalidEntityException;
	    
	    Student removeActiveExamFromStudent(Long examId, Long studentId) throws InvalidEntityException;
}
