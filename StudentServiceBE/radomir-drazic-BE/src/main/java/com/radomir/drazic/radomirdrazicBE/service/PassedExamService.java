package com.radomir.drazic.radomirdrazicBE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.radomir.drazic.radomirdrazicBE.dto.PassedExamDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;

public interface PassedExamService {

	List<PassedExamDto> findAllPassedExams();
    
    Page<PassedExamDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder, Long id);

    List<PassedExamDto> findOnlySomePassedExams(int number);

    Optional<PassedExamDto> findById(Long id) throws InvalidEntityException;

    PassedExamDto addExam(PassedExamDto exam) throws EntityExistsException;

    void deletePassedExam(Long id) throws InvalidEntityException;

    PassedExamDto editPassedExam(PassedExamDto exam) throws InvalidEntityException;
    
}
