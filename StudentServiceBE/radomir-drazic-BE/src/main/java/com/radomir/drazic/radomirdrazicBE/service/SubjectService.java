package com.radomir.drazic.radomirdrazicBE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.radomir.drazic.radomirdrazicBE.dto.SubjectDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;

public interface SubjectService {

	List<SubjectDto> getAllSubjects();
	
	Page<SubjectDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);
	
	List<SubjectDto> findOnlySomeSubjects(int number);
	
	SubjectDto addSubject(SubjectDto subject) throws EntityExistsException;
	
	void deleteSubject(Long id) throws InvalidEntityException;
	
	Optional<SubjectDto> findById(Long subjectId) throws InvalidEntityException;
	
	SubjectDto editSubject(SubjectDto subject) throws InvalidEntityException;
}
