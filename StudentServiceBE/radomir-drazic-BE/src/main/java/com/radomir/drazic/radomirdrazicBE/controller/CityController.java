package com.radomir.drazic.radomirdrazicBE.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radomir.drazic.radomirdrazicBE.dto.CityDto;
import com.radomir.drazic.radomirdrazicBE.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping()
	public List<CityDto> findAll(){
		return cityService.findAllCities();
	}
}
