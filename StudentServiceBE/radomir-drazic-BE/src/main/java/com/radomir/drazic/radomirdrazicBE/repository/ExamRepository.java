package com.radomir.drazic.radomirdrazicBE.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radomir.drazic.radomirdrazicBE.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

	List<Exam> findExamsByActiveStudentsStudentId(Long id);

	@Query(value = "SELECT e.id, e.date, e.exam_term, e.subject FROM active_exams ae INNER JOIN exam e on e.id = ae.id", nativeQuery=true)
	List<Exam> findActiveExams();
	
}
