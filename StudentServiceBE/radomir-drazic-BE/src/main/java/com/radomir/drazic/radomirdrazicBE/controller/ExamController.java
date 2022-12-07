package com.radomir.drazic.radomirdrazicBE.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.radomir.drazic.radomirdrazicBE.dto.ExamDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.service.ExamService;
import com.radomir.drazic.radomirdrazicBE.service.ProfessorService;
import com.radomir.drazic.radomirdrazicBE.service.StudentService;

@RestController
@RequestMapping("exams")
public class ExamController {

	@Autowired
	private ExamService examService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping()
	public List<ExamDto> findAll(){
		return examService.findAllExams();
	}
	
	@GetMapping("{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws Exception {
		Optional<ExamDto> exam = examService.findById(id);
		return exam.<ResponseEntity<Object>>map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID!"));
	}
	
	@GetMapping("filter/{id}")
	public ResponseEntity<Page<ExamDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, 
			@RequestParam(defaultValue = "name") String sortBy, 
			@RequestParam(defaultValue = "asc") String sortOrder,
			@PathVariable Long id) {
		return new ResponseEntity<Page<ExamDto>>(examService.findAll(pageNo, pageSize, sortBy, sortOrder, id), new HttpHeaders(),
				HttpStatus.OK);
	}

    @GetMapping(value = "pages/{number}")
    public List<ExamDto> findOnlySome(@PathVariable int number) {
        return examService.findOnlySomeExams(number);
    }

	@PostMapping()
	  public @ResponseBody ResponseEntity<Object> addExam(@Valid @RequestBody ExamDto examDto) {
	        try {
	        	if(!(examDto.getDate().after(examDto.getExamTerm().getStartDate()) && examDto.getDate().before(examDto.getExamTerm().getEndDate()))) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date");
				}
	        	ExamDto exam = examService.addExam(examDto);
	            return ResponseEntity.status(HttpStatus.OK).body(exam);
	        } catch (EntityExistsException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
	  
	@PutMapping("{id}")
		public @ResponseBody ResponseEntity<Object> editExam(@Valid @RequestBody ExamDto exam){
			try {
				if(!(exam.getDate().after(exam.getExamTerm().getStartDate()) && exam.getDate().before(exam.getExamTerm().getEndDate()))) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date");
				}
				ExamDto examDto = examService.editExam(exam);
				return ResponseEntity.status(HttpStatus.OK).body(examDto);
			}catch(InvalidEntityException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
	  
	@DeleteMapping("{id}")
		public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
			try {
				examService.deleteExam(id);
				return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + id);
			}catch(InvalidEntityException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
	
	 @GetMapping("{id}/activeexams")
	  public ResponseEntity<List<ExamDto>> getAllActiveExamsByStudentId(@PathVariable(value = "id") Long id, Long examId) throws InvalidEntityException {
	    if(studentService.findById(id).isEmpty()) {
	    	throw new InvalidEntityException(id, "Student doesnt exist");
	    }
	    List<ExamDto> exams = examService.findExamsByStudentId(id);
	   
//	    for(Exam exam : exams) {
//	    	 Optional<Student> student = Optional.of(studentService.removeActiveExamFromStudent( exam.getId(), id));
//	    	 studentRepository.save(student.get());
//	    }
	    return new ResponseEntity<>(exams, HttpStatus.OK);
	  }
	 
	 @GetMapping("{id}/exams")
	 public ResponseEntity<List<ExamDto>> getExamsByYearOfStudy(@PathVariable Long id, Integer yearOfStudy) throws InvalidEntityException{
		 if(studentService.findById(id).isEmpty()) {
			 throw new InvalidEntityException(id, "Student doesn't exist");
		 }
		 List<ExamDto> exams = examService.findExamsBySubjectYear(id);
		 return new ResponseEntity<>(exams, HttpStatus.OK);
	 }
	 
	 @GetMapping("{id}/professors")
	 public ResponseEntity<List<ExamDto>> getActiveExamsByProfessor(@PathVariable Long id) throws InvalidEntityException {
		 if(professorService.findById(id).isEmpty()) {
			 throw new InvalidEntityException(id, "Professor doesn't exist");
		 }
		 List<ExamDto> exams = examService.findActiveExamsByProfessor(id);
		 return new ResponseEntity<>(exams, HttpStatus.OK);
	 }
}
