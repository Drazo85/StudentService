package com.radomir.drazic.radomirdrazicBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radomir.drazic.radomirdrazicBE.dto.TitleDto;
import com.radomir.drazic.radomirdrazicBE.service.TitleService;

@RestController
@RequestMapping("titles")
public class TitleController {

	@Autowired
	private TitleService titleService;
	
	@GetMapping()
	public List<TitleDto> findAll(){
		return titleService.findAllTitles();
	}
}
