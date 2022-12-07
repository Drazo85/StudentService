package com.radomir.drazic.radomirdrazicBE.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radomir.drazic.radomirdrazicBE.dto.CityDto;
import com.radomir.drazic.radomirdrazicBE.entity.City;
import com.radomir.drazic.radomirdrazicBE.mapper.CityMapper;
import com.radomir.drazic.radomirdrazicBE.repository.CityRepository;
import com.radomir.drazic.radomirdrazicBE.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public List<CityDto> findAllCities() {
		List<City> cities = cityRepository.findAll();
		return cities.stream().map(cityMapper::toDto).collect(Collectors.toList());
	}
}
