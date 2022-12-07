package com.radomir.drazic.radomirdrazicBE.service;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.radomir.drazic.radomirdrazicBE.dto.ExamDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;

public interface ExamService {
	
	List<ExamDto> findAllExams();
    
    Page<ExamDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder, Long id);

    List<ExamDto> findOnlySomeExams(int number);

    Optional<ExamDto> findById(Long id) throws InvalidEntityException;

    ExamDto addExam(ExamDto exam) throws EntityExistsException;

    void deleteExam(Long id) throws InvalidEntityException;

    ExamDto editExam(ExamDto exam) throws InvalidEntityException;
    
    List<ExamDto> findExamsByStudentId(Long id) throws InvalidEntityException;
    
    List<ExamDto> findExamsBySubjectYear(Long studentId);
    
    List<ExamDto> findActiveExamsByProfessor(Long professorId);
    
}
