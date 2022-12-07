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

import com.radomir.drazic.radomirdrazicBE.dto.ExamTermDto;
import com.radomir.drazic.radomirdrazicBE.entity.ExamTerm;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.mapper.ExamTermMapper;
import com.radomir.drazic.radomirdrazicBE.repository.ExamTermRepository;
import com.radomir.drazic.radomirdrazicBE.service.ExamTermService;

@Service
public class ExamTermServiceImpl implements ExamTermService{

	@Autowired
	private ExamTermRepository examTermRepository;
	@Autowired
	private ExamTermMapper examTermMapper;
	
	@Override
	public List<ExamTermDto> findAllExamTerms() {
		List<ExamTerm> examTerms = examTermRepository.findAll();
		return examTerms.stream().map(examTermMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<ExamTermDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
		Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<ExamTermDto> entity = examTermRepository.findAll(pageable).map(examTermMapper::toDto);
		
		return entity;
	}

	@Override
	public List<ExamTermDto> findOnlySomeExamTerms(int number) {
		  Page<ExamTerm> page = examTermRepository.findAll(
	                PageRequest.of(number + 1, 2, Sort.by(Sort.Direction.ASC, "id")));
	        return page.stream().map(examTermMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ExamTermDto> findById(Long id) throws InvalidEntityException {
		Optional<ExamTerm> examTerm = examTermRepository.findById(id);
		return examTerm.map(examTermMapper::toDto);
	}

	@Override
	public ExamTermDto addExamTerm(ExamTermDto examTerm) throws EntityExistsException {
		return examTermMapper.toDtoNoId(examTermRepository.save(examTermMapper.toEntityNoId(examTerm)));
	}

	@Override
	public void deleteExamTerm(Long id) throws InvalidEntityException {
		Optional<ExamTerm> examTerm = examTermRepository.findById(id);
		if(examTerm.isEmpty()) {
			throw new InvalidEntityException(examTerm, "Invalid examTerm" + examTerm);
		}
		examTermRepository.delete(examTerm.get());
	}

	@Override
	public ExamTermDto editExamTerm(ExamTermDto examTerm) throws InvalidEntityException {
		if(examTermRepository.existsById(examTerm.getExamTermId())) {
			return examTermMapper.toDto(examTermRepository.save(examTermMapper.toEntity(examTerm)));
		}
		throw new InvalidEntityException(examTerm, "Exam term doesn't exists");
	}
}
