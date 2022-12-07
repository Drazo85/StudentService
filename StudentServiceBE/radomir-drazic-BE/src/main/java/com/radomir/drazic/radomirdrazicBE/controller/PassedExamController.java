package com.radomir.drazic.radomirdrazicBE.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.radomir.drazic.radomirdrazicBE.dto.PassedExamDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.service.PassedExamService;

@RestController
@RequestMapping("passedexams")
public class PassedExamController {

	@Autowired
	private PassedExamService passedExamService;
	
	@GetMapping()
	public List<PassedExamDto> findAll(){
		return passedExamService.findAllPassedExams();
	}
	
	@GetMapping("{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws Exception {
		Optional<PassedExamDto> exam = passedExamService.findById(id);
		return exam.<ResponseEntity<Object>>map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID!"));
	}
	

	@PostMapping()
	  public @ResponseBody ResponseEntity<Object> addExam(@Valid @RequestBody PassedExamDto examDto) {
	        try {
	        	PassedExamDto exam = passedExamService.addExam(examDto);
	            return ResponseEntity.status(HttpStatus.OK).body(exam);
	        } catch (EntityExistsException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
	  
	@PutMapping()
		public @ResponseBody ResponseEntity<Object> editPassedExam(@Valid @RequestBody PassedExamDto exam){
			try {
				PassedExamDto examDto = passedExamService.editPassedExam(exam);
				return ResponseEntity.status(HttpStatus.OK).body(examDto);
			}catch(InvalidEntityException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
	  
	@DeleteMapping("{id}")
		public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
			try {
			passedExamService.deletePassedExam(id);
				return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + id);
			}catch(InvalidEntityException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
}
