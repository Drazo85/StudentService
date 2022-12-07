package com.radomir.drazic.radomirdrazicBE.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radomir.drazic.radomirdrazicBE.dto.StudentDto;
import com.radomir.drazic.radomirdrazicBE.entity.Student;

@Component
public class StudentMapper implements GenericMapper<Student, StudentDto>{
	
	@Autowired
	private CityMapper cityMapper;
	

	@Override
	public Student toEntity(StudentDto dto) {
		return new Student(dto.getStudentId(), dto.getIndexNumber(), dto.getIndexYear(),
				dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getAddress(),
				cityMapper.toEntity(dto.getCity()), dto.getYearOfStudy());
	}

	@Override
	public StudentDto toDto(Student entity) {
		
		return new StudentDto(entity.getStudentId(), entity.getIndexNumber(), entity.getIndexYear(),
				entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getAddress(),
				cityMapper.toDto(entity.getCity()), entity.getYearOfStudy());
	}
	
	
	public Student toEntityNoId(StudentDto dto) {
		return new Student(dto.getIndexNumber(), dto.getIndexYear(),
				dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getAddress(),
				cityMapper.toEntity(dto.getCity()), dto.getYearOfStudy());
	}

	public StudentDto toDtoNoId(Student entity) {
		
		return new StudentDto(entity.getIndexNumber(), entity.getIndexYear(),
				entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getAddress(),
				cityMapper.toDto(entity.getCity()), entity.getYearOfStudy());
	}

}
