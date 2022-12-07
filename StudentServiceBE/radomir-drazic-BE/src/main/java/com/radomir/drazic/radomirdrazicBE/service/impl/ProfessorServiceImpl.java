package com.radomir.drazic.radomirdrazicBE.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.radomir.drazic.radomirdrazicBE.dto.ProfessorDto;
import com.radomir.drazic.radomirdrazicBE.entity.Professor;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.mapper.ProfessorMapper;
import com.radomir.drazic.radomirdrazicBE.repository.ProfessorRepository;
import com.radomir.drazic.radomirdrazicBE.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private ProfessorMapper professorMapper;
	
	
	@Override
	public List<ProfessorDto> findAllProfessors() {
		List<Professor> professors = professorRepository.findAll();
		return professors.stream().map(professorMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<ProfessorDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
		Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<ProfessorDto> entity = professorRepository.findAll(pageable).map(professorMapper::toDto);
		
		return entity;
	}

	@Override
	public List<ProfessorDto> findOnlySomeProfessors(int number) {
		  Page<Professor> page = professorRepository.findAll(
	                PageRequest.of(number + 1, 2, Sort.by(Sort.Direction.ASC, "id")));
	        return page.stream().map(professorMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ProfessorDto> findById(Long id) throws InvalidEntityException {
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.map(professorMapper::toDto);
	}

	@Override
	public ProfessorDto addProfessor(ProfessorDto professor) throws EntityExistsException {
		return professorMapper.toDtoNoId(professorRepository.save(professorMapper.toEntityNoId(professor)));
	}

	@Override
	public void deleteProfessor(Long id) throws InvalidEntityException {
		Optional<Professor> professor = professorRepository.findById(id);
		if(professor.isEmpty()) {
			throw new InvalidEntityException(professor, "Invalid professor" + professor);
		}
		professorRepository.delete(professor.get());
	}

	@Override
	public ProfessorDto editProfessor(ProfessorDto professor) throws InvalidEntityException {
		if(professorRepository.existsById(professor.getId())) {
			return professorMapper.toDto(professorRepository.save(professorMapper.toEntity(professor)));
		}
		throw new InvalidEntityException(professor, "Professor doesn't exists");
	}
}
