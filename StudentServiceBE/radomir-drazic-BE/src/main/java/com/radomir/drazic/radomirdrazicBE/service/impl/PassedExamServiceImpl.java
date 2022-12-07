package com.radomir.drazic.radomirdrazicBE.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.radomir.drazic.radomirdrazicBE.dto.PassedExamDto;
import com.radomir.drazic.radomirdrazicBE.entity.PassedExam;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.mapper.PassedExamMapper;
import com.radomir.drazic.radomirdrazicBE.repository.PassedExamRepository;
import com.radomir.drazic.radomirdrazicBE.service.PassedExamService;

@Service
public class PassedExamServiceImpl implements PassedExamService {
	
	@Autowired
	private PassedExamRepository passedExamRepository;
	@Autowired
	private PassedExamMapper passedExamMapper;
	
	@Override
	public List<PassedExamDto> findAllPassedExams() {
		List<PassedExam> exams = passedExamRepository.findAll();
		return exams.stream().map(passedExamMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<PassedExamDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PassedExamDto> findOnlySomePassedExams(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PassedExamDto> findById(Long id) throws InvalidEntityException {
		Optional<PassedExam> exam = passedExamRepository.findById(id);
		return exam.map(passedExamMapper::toDto);
	}

	@Override
	public PassedExamDto addExam(PassedExamDto exam) throws EntityExistsException {
		return passedExamMapper.toDtoNoId(passedExamRepository.save(passedExamMapper.toEntityNoId(exam)));

	}

	@Override
	public void deletePassedExam(Long id) throws InvalidEntityException {
		Optional<PassedExam> exam = passedExamRepository.findById(id);
		if(exam.isEmpty()) {
			throw new InvalidEntityException(exam, "Invalid examTerm" + exam);
		}
		passedExamRepository.delete(exam.get());
	}

	@Override
	public PassedExamDto editPassedExam(PassedExamDto exam) throws InvalidEntityException {
		if(passedExamRepository.existsById(exam.getPassedExamId())) {
			return passedExamMapper.toDto(passedExamRepository.save(passedExamMapper.toEntity(exam)));
		}
		throw new InvalidEntityException(exam, "Exam doesn't exists");
	}

}
