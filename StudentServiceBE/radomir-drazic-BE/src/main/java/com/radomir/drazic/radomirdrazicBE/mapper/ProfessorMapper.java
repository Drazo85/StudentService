package com.radomir.drazic.radomirdrazicBE.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radomir.drazic.radomirdrazicBE.dto.ProfessorDto;
import com.radomir.drazic.radomirdrazicBE.entity.Professor;

@Component
public class ProfessorMapper implements GenericMapper<Professor, ProfessorDto>{

	@Autowired
	private CityMapper cityMapper;
//	@Autowired
//	private TitleMapper titleMapper;
	@Autowired
	private ITitleMapper iTitleMapper;


	@Override
	public Professor toEntity(ProfessorDto dto) {
		return new Professor(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(),
				dto.getAddress(), cityMapper.toEntity(dto.getCity()), dto.getPhoneNumber(),
				dto.getReelectionDate(), iTitleMapper.toEntity(dto.getTitle()));
	}

	@Override
	public ProfessorDto toDto(Professor entity) {
		
		return new ProfessorDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(),
				entity.getAddress(), cityMapper.toDto(entity.getCity()), entity.getPhoneNumber(),
				entity.getReelectionDate(), iTitleMapper.toDto(entity.getTitle()));
	}
	
	
	public Professor toEntityNoId(ProfessorDto dto) {
		return new Professor(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
				dto.getAddress(), cityMapper.toEntity(dto.getCity()), dto.getPhoneNumber(),
				dto.getReelectionDate(), iTitleMapper.toEntity(dto.getTitle()));
	}

	public ProfessorDto toDtoNoId(Professor entity) {
		
		return new ProfessorDto(entity.getFirstName(), entity.getLastName(), entity.getEmail(),
				entity.getAddress(), cityMapper.toDto(entity.getCity()), entity.getPhoneNumber(),
				entity.getReelectionDate(), iTitleMapper.toDto(entity.getTitle()));
	}	

}
