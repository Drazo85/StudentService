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

import com.radomir.drazic.radomirdrazicBE.dto.StudentDto;
import com.radomir.drazic.radomirdrazicBE.entity.Exam;
import com.radomir.drazic.radomirdrazicBE.entity.Student;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.mapper.StudentMapper;
import com.radomir.drazic.radomirdrazicBE.repository.StudentRepository;
import com.radomir.drazic.radomirdrazicBE.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public List<StudentDto> findAllStudents() {
		List<Student> students = studentRepository.findAll();
        return students.stream().map(studentMapper::toDto).collect(Collectors.toList());

	}

	@Override
	public Page<StudentDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
		Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<StudentDto> entity = studentRepository.findAll(pageable).map(studentMapper::toDto);
		
		return entity;
	}

	@Override
	public List<StudentDto> findOnlySomeStudents(int number) {
		 Page<Student> page = studentRepository.findAll(
	                PageRequest.of(number + 1, 2, Sort.by(Sort.Direction.ASC, "id")));
	        return page.stream().map(studentMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<StudentDto> findById(Long id) throws InvalidEntityException {
		Optional<Student> student = studentRepository.findById(id);
        return student.map(studentMapper::toDto);

	}

	@Override
	public StudentDto addStudent(StudentDto student) throws EntityExistsException {
		return studentMapper.toDtoNoId(studentRepository.save(studentMapper.toEntityNoId(student)));

	}

	@Override
	public void deleteStudent(Long id) throws InvalidEntityException {
		Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new InvalidEntityException(student, "Invalid student" + student);
        }
        studentRepository.delete(student.get());
	}

	@Override
	public StudentDto editStudent(StudentDto student) throws InvalidEntityException {
		 if (studentRepository.existsById(student.getStudentId())) {
	            return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(student)));
	        }
	        throw new InvalidEntityException(student, "Student doesn't exist!");
	}

	@Override
	public List<StudentDto> findStudentsByExamId(Long id) throws InvalidEntityException {
		List<Student> students = studentRepository.findStudentsByActiveExamsId(id);
        return students.stream().map(studentMapper::toDto).collect(Collectors.toList());
	}
	
	@Override
	public Student addActiveExamToStudent(Exam exam, Long id) throws InvalidEntityException{
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent()) {
			student.get().addActiveExam(exam); 
			 return studentRepository.save(student.get());
		}
		throw new InvalidEntityException(student, "Student doesn't exist!");
	}
	
	@Override
	public Student removeActiveExamFromStudent(Long examId, Long studentId) throws InvalidEntityException{
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isPresent()) {
			student.get().removeActiveExam(examId); 
			 return studentRepository.save(student.get());
		}
		throw new InvalidEntityException(student, "Student doesn't exist!");
	}
}
