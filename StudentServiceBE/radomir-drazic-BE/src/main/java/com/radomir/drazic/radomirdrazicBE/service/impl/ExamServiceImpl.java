package com.radomir.drazic.radomirdrazicBE.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.radomir.drazic.radomirdrazicBE.dto.ExamDto;
import com.radomir.drazic.radomirdrazicBE.entity.Exam;
import com.radomir.drazic.radomirdrazicBE.entity.Professor;
import com.radomir.drazic.radomirdrazicBE.entity.Student;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.mapper.ExamMapper;
import com.radomir.drazic.radomirdrazicBE.repository.ExamRepository;
import com.radomir.drazic.radomirdrazicBE.repository.ProfessorRepository;
import com.radomir.drazic.radomirdrazicBE.repository.StudentRepository;
import com.radomir.drazic.radomirdrazicBE.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private ExamMapper examMapper;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Override
	public List<ExamDto> findAllExams() {
		List<Exam> exams = examRepository.findAll();
		return exams.stream().map(examMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<ExamDto> findAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder, Long id) {
		Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		//List<ExamDto> list = examRepository.findAll().stream().filter(e -> e.getExamTerm().getExamTermId() == id).map(examMapper::toDto).toList();
		List<ExamDto> list = findExamsByExamTerm(id, pageable).stream().map(examMapper::toDto).toList();
		//List<ExamDto> listPage = list(pageable);
		//Page<ExamDto> entity = examRepository.findAll(pageable).map(examMapper::toDto);
		//List<ExamDto> streams = entity.filter(e -> e.getExamTerm().getExamTermId() == id).toList();
		//System.out.println(id);
		return new PageImpl<ExamDto>(list);
	}
	
	public List<Exam>findExamsByExamTerm(Long id, Pageable pageable){
		List<Exam> list = examRepository.findAll().stream().filter(e -> e.getExamTerm().getExamTermId() == id).toList();	
		return list;
	};

	@Override
	public List<ExamDto> findOnlySomeExams(int number) {
		Page<Exam> page = examRepository.findAll(
                PageRequest.of(number + 1, 2, Sort.by(Sort.Direction.ASC, "id")));
        return page.stream().map(examMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ExamDto> findById(Long id) throws InvalidEntityException {
		Optional<Exam> exam = examRepository.findById(id);
		return exam.map(examMapper::toDto);
	}

	@Override
	public ExamDto addExam(ExamDto exam) throws EntityExistsException {
		return examMapper.toDtoNoId(examRepository.save(examMapper.toEntityNoId(exam)));
	}

	@Override
	public void deleteExam(Long id) throws InvalidEntityException {
		Optional<Exam> exam = examRepository.findById(id);
		if(exam.isEmpty()) {
			throw new InvalidEntityException(exam, "Invalid examTerm" + exam);
		}
		examRepository.delete(exam.get());
	}

	@Override
	public ExamDto editExam(ExamDto exam) throws InvalidEntityException {
		if(examRepository.existsById(exam.getId())) {
			return examMapper.toDto(examRepository.save(examMapper.toEntity(exam)));
		}
		throw new InvalidEntityException(exam, "Exam doesn't exists");
	}

	@Override
	public List<ExamDto> findExamsByStudentId(Long id) throws InvalidEntityException {
		List<Exam> exams = examRepository.findExamsByActiveStudentsStudentId(id);
		//Optional<Student> student = studentRepository.findById(id);
//		for(Exam exam : exams) {
//			student.get().removeExamFromActiveByDate(exam);
//		}
        return exams.stream().map(examMapper::toDto).collect(Collectors.toList());
	}
	
	//novo
	@Override
	public List<ExamDto> findExamsBySubjectYear(Long studentId){
		Optional<Student> student = studentRepository.findById(studentId); 
		List<Exam> exams = examRepository.findAll().stream().filter(e -> e.getSubject().getYearOfStudy() <= student.get().getYearOfStudy()).toList();
		return exams.stream().map(examMapper::toDto).toList();
	}

	@Override
	public List<ExamDto> findActiveExamsByProfessor(Long professorId) {
		Optional<Professor> professor = professorRepository.findById(professorId);
		List<Exam> exams = examRepository.findActiveExams().stream().filter(e -> e.getSubject().getProfessor() == professor.get()).toList();
		return exams.stream().map(examMapper::toDto).toList();
	}
}
