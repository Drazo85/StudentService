package com.radomir.drazic.radomirdrazicBE.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radomir.drazic.radomirdrazicBE.dto.ExamDto;
import com.radomir.drazic.radomirdrazicBE.entity.Exam;

@Component
public class ExamMapper implements GenericMapper<Exam, ExamDto>{
	
	@Autowired
	private SubjectMapper subjectMapper;
	@Autowired
	private ExamTermMapper examTermMapper;
	
	
	@Override
	public Exam toEntity(ExamDto dto) {
		return new Exam(examTermMapper.toEntity(dto.getExamTerm()),
				 subjectMapper.toEntity(dto.getSubject()),
				 dto.getId(), dto.getDate());
	}

	@Override
	public ExamDto toDto(Exam entity) {
		return new ExamDto(examTermMapper.toDto(entity.getExamTerm()),
				subjectMapper.toDto(entity.getSubject()),
				entity.getId(), entity.getDate());
	}
	
	
	public Exam toEntityNoId(ExamDto dto) {
		return new Exam(examTermMapper.toEntity(dto.getExamTerm()),
				subjectMapper.toEntity(dto.getSubject()),
				dto.getDate());
	}

	public ExamDto toDtoNoId(Exam entity) {
		return new ExamDto(examTermMapper.toDto(entity.getExamTerm()),
				subjectMapper.toDto(entity.getSubject()),
				entity.getDate());
	}
}
