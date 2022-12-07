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

import com.radomir.drazic.radomirdrazicBE.dto.SubjectDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.service.SubjectService;

@RestController
@RequestMapping("subjects")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@GetMapping()
	public List<SubjectDto> findAll(){
		return subjectService.getAllSubjects();
	}
	
	@GetMapping("{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws Exception{
		Optional<SubjectDto> subjectDto = subjectService.findById(id);
		return subjectDto.<ResponseEntity<Object>>map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID!"));
	}
	
	@GetMapping("filter")
	public ResponseEntity<Page<SubjectDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, 
			@RequestParam(defaultValue = "name") String sortBy, 
			@RequestParam(defaultValue = "asc") String sortOrder) {
		return new ResponseEntity<Page<SubjectDto>>(subjectService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
				HttpStatus.OK);
	}

    @GetMapping(value = "pages/{number}")
    public List<SubjectDto> findOnlySome(@PathVariable int number) {
        return subjectService.findOnlySomeSubjects(number);
    }
	
	@PostMapping()
	public @ResponseBody ResponseEntity<Object> addSubject(@Valid @RequestBody SubjectDto subjectDto) {
		try {
			SubjectDto subject = subjectService.addSubject(subjectDto);
			return ResponseEntity.status(HttpStatus.OK).body(subject);
		}catch(EntityExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody ResponseEntity<String> deleteSubject(@PathVariable Long id){
		try {
			subjectService.deleteSubject(id);
			return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + id);
		}catch(InvalidEntityException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> editSubject(@Valid @RequestBody SubjectDto subjectDto) {
		try {
			SubjectDto subject = subjectService.editSubject(subjectDto);
			return ResponseEntity.status(HttpStatus.OK).body(subject);
		}catch(InvalidEntityException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
