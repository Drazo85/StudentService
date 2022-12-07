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

import com.radomir.drazic.radomirdrazicBE.dto.StudentDto;
import com.radomir.drazic.radomirdrazicBE.entity.Exam;
import com.radomir.drazic.radomirdrazicBE.entity.PassedExam;
import com.radomir.drazic.radomirdrazicBE.entity.Student;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.repository.PassedExamRepository;
import com.radomir.drazic.radomirdrazicBE.repository.StudentRepository;
import com.radomir.drazic.radomirdrazicBE.service.ExamService;
import com.radomir.drazic.radomirdrazicBE.service.StudentService;

@RestController
@RequestMapping("students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired 
	private ExamService examService;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private PassedExamRepository passedExamRepository;
	
	@GetMapping()
    public List<StudentDto> findAll() {
        return studentService.findAllStudents();
    }
	
	@GetMapping("{id}")
    public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws Exception {
        Optional<StudentDto> studentDto = studentService.findById(id);
        return studentDto.<ResponseEntity<Object>>map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID!"));
    }
	
	@GetMapping("filter")
	public ResponseEntity<Page<StudentDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, 
			@RequestParam(defaultValue = "name") String sortBy, 
			@RequestParam(defaultValue = "asc") String sortOrder) {
		return new ResponseEntity<Page<StudentDto>>(studentService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
				HttpStatus.OK);
	}

    @GetMapping(value = "pages/{number}")
    public List<StudentDto> findOnlySome(@PathVariable int number) {
        return studentService.findOnlySomeStudents(number);
    }
	
	@PostMapping()
    public @ResponseBody ResponseEntity<Object> addStudent(@Valid @RequestBody StudentDto studentDto) throws InvalidEntityException {
        try {
        	Integer indexYearDto = studentDto.getIndexYear();
    		String indexNumberDto = studentDto.getIndexNumber();
    		String indexDto = (indexNumberDto + indexYearDto);
    		List<StudentDto> students = studentService.findAllStudents();
    		for (StudentDto studentDto2 : students) {
				Integer indexYear = studentDto2.getIndexYear();
				String indexNumber = studentDto2.getIndexNumber();
				String index = indexNumber + indexYear;
				if(indexDto == index) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Index number already exists!");
				}
			}
    		StudentDto student = studentService.addStudent(studentDto);
    		return ResponseEntity.status(HttpStatus.OK).body(student);
    		
    		
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
	
	@PutMapping("{id}")
    public @ResponseBody ResponseEntity<Object> editStudent(@Valid @RequestBody StudentDto studentDto) {
        try {
            StudentDto entity = studentService.editStudent(studentDto);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (InvalidEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
	
	 @DeleteMapping("{id}")
	    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
	        try {
	            studentService.deleteStudent(id);
	            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + id);
	        } catch (InvalidEntityException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
	 
	 @GetMapping("{id}/activestudents")
	  public ResponseEntity<List<StudentDto>> getAllActiveStudentsByExamId(@PathVariable(value = "id") Long id) throws InvalidEntityException {
	    if(examService.findById(id).isEmpty()) {
	    	throw new InvalidEntityException(id, "Exam doesnt exist");
	    }
	    List<StudentDto> studentDto = studentService.findStudentsByExamId(id);
	    return new ResponseEntity<>(studentDto, HttpStatus.OK);
	  }
	 
	 @PostMapping("{id}/activeexams")
	 public @ResponseBody ResponseEntity<Object> addActiveExamToStudent(@Valid @RequestBody Exam exam, @PathVariable Long id) throws InvalidEntityException {
	        try {
	        	Optional<Student> student = studentRepository.findById(id);
	        	if(student.isEmpty()) {
	        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty");
	        	}
	        	List<PassedExam> passedExams = passedExamRepository.findAll();
	        	for(PassedExam passedExam : passedExams) {
	        		if(passedExam.getStudent().getStudentId() == id && passedExam.getExam().getSubject().getSubjectId() == exam.getSubject().getSubjectId()) {
	        			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exam already passed");
	        		}
	        	}
//	        	student = Optional.of(studentService.addActiveExamToStudent(exam, id));
	        	studentService.addActiveExamToStudent(exam, id);
	        	Student entity = studentRepository.save(student.get());
	        	
	        	return ResponseEntity.status(HttpStatus.OK).body(entity);
	        }catch(InvalidEntityException e) {
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }

	 @PutMapping("{id}/activeexams/{examId}")
	 public @ResponseBody ResponseEntity<Object> removeActiveExamFromStudent(@Valid @PathVariable Long examId, @PathVariable Long id) throws InvalidEntityException {
	        Optional<Student> student = studentRepository.findById(id);
			if(student.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty");
			}
			studentService.removeActiveExamFromStudent(examId, id);
			Student entity = studentRepository.save(student.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(entity);
	    }
}
