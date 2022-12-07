package com.radomir.drazic.radomirdrazicBE.controller;

import java.util.Calendar;
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

import com.radomir.drazic.radomirdrazicBE.dto.ExamTermDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.service.ExamTermService;

@RestController
@RequestMapping("examterms")
public class ExamTermController {

	@Autowired
	private ExamTermService examTermService;
	
	@GetMapping()
	public List<ExamTermDto> findAll(){
		return examTermService.findAllExamTerms();
	}
	
	@GetMapping("{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws Exception {
		Optional<ExamTermDto> examTerm = examTermService.findById(id);
		return examTerm.<ResponseEntity<Object>>map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID!"));
	}
	
	@GetMapping("filter")
	public ResponseEntity<Page<ExamTermDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, 
			@RequestParam(defaultValue = "name") String sortBy, 
			@RequestParam(defaultValue = "asc") String sortOrder) {
		return new ResponseEntity<Page<ExamTermDto>>(examTermService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
				HttpStatus.OK);
	}

    @GetMapping(value = "pages/{number}")
    public List<ExamTermDto> findOnlySome(@PathVariable int number) {
        return examTermService.findOnlySomeExamTerms(number);
    }

	@PostMapping()
	   public @ResponseBody ResponseEntity<Object> addExamTerm(@Valid @RequestBody ExamTermDto examTermDto) {
	      try {
	       	Calendar startDateDto = examTermDto.getStartDate();
	       	Calendar endDateDto = examTermDto.getEndDate();
	       	List<ExamTermDto> examTermDtos = examTermService.findAllExamTerms();
	       	if(startDateDto.after(endDateDto)) {
	       		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date!");
	       	}else {
	       	for(ExamTermDto examTermDto2 : examTermDtos) {
	       		Calendar startDateDto2 = examTermDto2.getStartDate();
	       		Calendar endDateDto2 = examTermDto2.getEndDate();
        		if((startDateDto.after(startDateDto2) && startDateDto.before(endDateDto2)) || (endDateDto.after(startDateDto2) && endDateDto.before(endDateDto2)))
        			{
        			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date!");
        			}
        		else if((startDateDto2.after(startDateDto) && startDateDto2.before(endDateDto)) || ((endDateDto2.after(startDateDto)) && endDateDto2.before(endDateDto2)))
        			{
	        			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date!");
	        		}
	        	}
	       	}
	        	ExamTermDto examTerm = examTermService.addExamTerm(examTermDto);
	            return ResponseEntity.status(HttpStatus.OK).body(examTerm);
	        } catch (EntityExistsException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
	  
	  @PutMapping("{id}")
		public @ResponseBody ResponseEntity<Object> editExamTerm(@Valid @RequestBody ExamTermDto examTerm){
			try {
				Calendar startDateDto = examTerm.getStartDate();
		       	Calendar endDateDto = examTerm.getEndDate();
		       	List<ExamTermDto> examTermDtos = examTermService.findAllExamTerms();
		       	if(startDateDto.after(endDateDto)) {
		       		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date!");
		       	}else {
		       	for(ExamTermDto examTermDto2 : examTermDtos) {
		       		Calendar startDateDto2 = examTermDto2.getStartDate();
		       		Calendar endDateDto2 = examTermDto2.getEndDate();
	        		if((startDateDto.after(startDateDto2) && startDateDto.before(endDateDto2)) || (endDateDto.after(startDateDto2) && endDateDto.before(endDateDto2)))
	        			{
	        			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date!");
	        			}
	        		else if((startDateDto2.after(startDateDto) && startDateDto2.before(endDateDto)) || ((endDateDto2.after(startDateDto)) && endDateDto2.before(endDateDto2)))
	        			{
		        			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date!");
		        		}
		        	}
		       	}
				ExamTermDto examTermDto = examTermService.editExamTerm(examTerm);
				return ResponseEntity.status(HttpStatus.OK).body(examTermDto);
			}catch(InvalidEntityException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
	  
	  @DeleteMapping("{id}")
		public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
			try {
				examTermService.deleteExamTerm(id);
				return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + id);
			}catch(InvalidEntityException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
}
