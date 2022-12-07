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

import com.radomir.drazic.radomirdrazicBE.dto.SubjectDto;
import com.radomir.drazic.radomirdrazicBE.entity.Subject;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.mapper.SubjectMapper;
import com.radomir.drazic.radomirdrazicBE.repository.SubjectRepository;
import com.radomir.drazic.radomirdrazicBE.service.SubjectService;


@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private SubjectMapper subjectMapper;
	
	@Override
	public List<SubjectDto> getAllSubjects() {
		List<Subject> subjects = subjectRepository.findAll();
		return subjects.stream().map(subjectMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<SubjectDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
		Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<SubjectDto> entity = subjectRepository.findAll(pageable).map(subjectMapper::toDto);
		
		return entity;
	}
	
	@Override
	public List<SubjectDto> findOnlySomeSubjects(int number) {
		Page<Subject> page = subjectRepository.findAll(
                PageRequest.of(number + 1, 2, Sort.by(Sort.Direction.ASC, "id")));
        return page.stream().map(subjectMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public SubjectDto addSubject(SubjectDto subject) throws EntityExistsException {
		return subjectMapper.toDtoNoId(subjectRepository.save(subjectMapper.toEntityNoId(subject)));
	}

	@Override
	public void deleteSubject(Long id) throws InvalidEntityException {
		Optional<Subject> subject = subjectRepository.findById(id);
		if(subject.isEmpty()) {
			throw new InvalidEntityException(subject, "Invalid subject" + subject);
		}
		subjectRepository.delete(subject.get());
	}

	@Override
	public Optional<SubjectDto> findById(Long subjectId) throws InvalidEntityException {
		Optional<Subject> subject = subjectRepository.findById(subjectId);
		return subject.map(subjectMapper::toDto);
	}

	@Override
	public SubjectDto editSubject(SubjectDto subject) throws InvalidEntityException {
		if(subjectRepository.existsById(subject.getSubjectId())) {
			return subjectMapper.toDto(subjectRepository.save(subjectMapper.toEntity(subject)));
		}
		throw new InvalidEntityException(subject, "Subject doesn't exist!");
	}
}
