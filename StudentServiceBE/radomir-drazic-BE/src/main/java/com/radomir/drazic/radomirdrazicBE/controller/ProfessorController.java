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

import com.radomir.drazic.radomirdrazicBE.dto.ProfessorDto;
import com.radomir.drazic.radomirdrazicBE.exceptions.EntityExistsException;
import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;
import com.radomir.drazic.radomirdrazicBE.service.ProfessorService;

@RestController
@RequestMapping("professors")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@GetMapping()
	public List<ProfessorDto> findAll(){
		return professorService.findAllProfessors();
	}
	
	@GetMapping("{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) throws Exception {
		Optional<ProfessorDto> professor = professorService.findById(id);
		return professor.<ResponseEntity<Object>>map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID!"));
	}
	
	@GetMapping("filter")
	public ResponseEntity<Page<ProfessorDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, 
			@RequestParam(defaultValue = "name") String sortBy, 
			@RequestParam(defaultValue = "asc") String sortOrder) {
		return new ResponseEntity<Page<ProfessorDto>>(professorService.findAll(pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
				HttpStatus.OK);
	}

    @GetMapping(value = "pages/{number}")
    public List<ProfessorDto> findOnlySome(@PathVariable int number) {
        return professorService.findOnlySomeProfessors(number);
    }
	
	@PostMapping()
	public @ResponseBody ResponseEntity<Object> addProfessor(@Valid @RequestBody ProfessorDto professorDto) {
	    try {
	    		ProfessorDto professor = professorService.addProfessor(professorDto);
            	return ResponseEntity.status(HttpStatus.OK).body(professor);
	        } catch (EntityExistsException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	 }
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> editProfessor(@Valid @RequestBody ProfessorDto professor){
		try {
			ProfessorDto professorDto = professorService.editProfessor(professor);
			return ResponseEntity.status(HttpStatus.OK).body(professorDto);
		}catch(InvalidEntityException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			professorService.deleteProfessor(id);
			return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + id);
		}catch(InvalidEntityException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
