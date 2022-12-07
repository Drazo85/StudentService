package com.radomir.drazic.radomirdrazicBE.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.radomir.drazic.radomirdrazicBE.dto.ProfessorDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;


public interface ProfessorService {

	List<ProfessorDto> findAllProfessors();
    
    Page<ProfessorDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

    List<ProfessorDto> findOnlySomeProfessors(int number);

    Optional<ProfessorDto> findById(Long id) throws InvalidEntityException;

    ProfessorDto addProfessor(ProfessorDto professor) throws EntityExistsException;

    void deleteProfessor(Long id) throws InvalidEntityException;

    ProfessorDto editProfessor(ProfessorDto professor) throws InvalidEntityException;
}
