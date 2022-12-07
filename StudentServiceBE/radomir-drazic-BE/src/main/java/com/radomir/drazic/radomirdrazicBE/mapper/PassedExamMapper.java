package com.radomir.drazic.radomirdrazicBE.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radomir.drazic.radomirdrazicBE.dto.PassedExamDto;
import com.radomir.drazic.radomirdrazicBE.entity.PassedExam;

@Component
public class PassedExamMapper implements GenericMapper<PassedExam, PassedExamDto>{

	@Autowired
	private ExamMapper examMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public PassedExam toEntity(PassedExamDto dto) {
		return new PassedExam(dto.getPassedExamId(), examMapper.toEntity(dto.getExam()), 
				studentMapper.toEntity(dto.getStudent()), dto.getGrade());
	}

	@Override
	public PassedExamDto toDto(PassedExam entity) {
		return new PassedExamDto(entity.getPassedExamId(), examMapper.toDto(entity.getExam()), 
				studentMapper.toDto(entity.getStudent()), entity.getGrade());
	}
	
	
	public PassedExam toEntityNoId(PassedExamDto dto) {
		return new PassedExam(examMapper.toEntity(dto.getExam()), 
				studentMapper.toEntity(dto.getStudent()), dto.getGrade());
	}

	public PassedExamDto toDtoNoId(PassedExam entity) {
		return new PassedExamDto(examMapper.toDto(entity.getExam()), 
				studentMapper.toDto(entity.getStudent()), entity.getGrade());
	}

	
}
