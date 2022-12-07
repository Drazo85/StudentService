package com.radomir.drazic.radomirdrazicBE.service;

import java.util.List;

import com.radomir.drazic.radomirdrazicBE.dto.CityDto;

public interface CityService {

	List<CityDto> findAllCities();
}
