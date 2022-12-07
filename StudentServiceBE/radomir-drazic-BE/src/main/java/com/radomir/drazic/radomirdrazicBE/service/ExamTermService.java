package com.radomir.drazic.radomirdrazicBE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.radomir.drazic.radomirdrazicBE.dto.ExamTermDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;

public interface ExamTermService {

	List<ExamTermDto> findAllExamTerms();
    
    Page<ExamTermDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

    List<ExamTermDto> findOnlySomeExamTerms(int number);

    Optional<ExamTermDto> findById(Long id) throws InvalidEntityException;

    ExamTermDto addExamTerm(ExamTermDto examTerm) throws EntityExistsException;

    void deleteExamTerm(Long id) throws InvalidEntityException;

    ExamTermDto editExamTerm(ExamTermDto examTerm) throws InvalidEntityException;
}
