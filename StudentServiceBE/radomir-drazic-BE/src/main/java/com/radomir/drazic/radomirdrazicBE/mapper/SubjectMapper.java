package com.radomir.drazic.radomirdrazicBE.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radomir.drazic.radomirdrazicBE.dto.SubjectDto;
import com.radomir.drazic.radomirdrazicBE.entity.Subject;

@Component
public class SubjectMapper implements GenericMapper<Subject, SubjectDto> {

	@Autowired
	private ProfessorMapper professorMapper;
	
	@Override
	public Subject toEntity(SubjectDto dto) {
		return new Subject(dto.getSubjectId(), dto.getName(), dto.getDescription(), 
				dto.getNoOfESP(), dto.getYearOfStudy(), dto.getSemester(), professorMapper.toEntity(dto.getProfessor()));
	}

	@Override
	public SubjectDto toDto(Subject entity) {
		
		return new SubjectDto(entity.getSubjectId(), entity.getName(), entity.getDescription(),
				entity.getNoOfESP(), entity.getYearOfStudy(), entity.getSemester(), professorMapper.toDto(entity.getProfessor()));
	}
	
	public Subject toEntityNoId(SubjectDto dto) {
		return new Subject( dto.getName(), dto.getDescription(), 
				dto.getNoOfESP(), dto.getYearOfStudy(), dto.getSemester(), professorMapper.toEntity(dto.getProfessor()));
	}

	public SubjectDto toDtoNoId(Subject entity) {
		
		return new SubjectDto(entity.getName(), entity.getDescription(),
				entity.getNoOfESP(), entity.getYearOfStudy(), entity.getSemester(), professorMapper.toDto(entity.getProfessor()));
	}
	
}
