package com.radomir.drazic.radomirdrazicBE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radomir.drazic.radomirdrazicBE.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	List<Student> findStudentsByActiveExamsId(Long id);
}
