package com.radomir.drazic.radomirdrazicBE.mapper;

import org.springframework.stereotype.Component;

import com.radomir.drazic.radomirdrazicBE.dto.ExamTermDto;
import com.radomir.drazic.radomirdrazicBE.entity.ExamTerm;

@Component
public class ExamTermMapper implements GenericMapper<ExamTerm, ExamTermDto> {
	
	@Override
	public ExamTerm toEntity(ExamTermDto dto) {
		return new ExamTerm(dto.getExamTermId(), dto.getName(), dto.getStartDate(),
				dto.getEndDate());
	}

	@Override
	public ExamTermDto toDto(ExamTerm entity) {
		return new ExamTermDto(entity.getExamTermId(), entity.getName(), entity.getStartDate(),
				entity.getEndDate());
	}
	
	
	public ExamTerm toEntityNoId(ExamTermDto dto) {
		return new ExamTerm(dto.getName(), dto.getStartDate(),
				dto.getEndDate());
	}

	public ExamTermDto toDtoNoId(ExamTerm entity) {

		return new ExamTermDto(entity.getName(), entity.getStartDate(),
				entity.getEndDate());
	}
}
