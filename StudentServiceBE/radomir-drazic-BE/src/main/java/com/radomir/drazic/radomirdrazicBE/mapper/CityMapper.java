package com.radomir.drazic.radomirdrazicBE.mapper;

import org.springframework.stereotype.Component;

import com.radomir.drazic.radomirdrazicBE.dto.CityDto;
import com.radomir.drazic.radomirdrazicBE.entity.City;

@Component
public class CityMapper implements GenericMapper<City, CityDto> {
    @Override
    public City toEntity(CityDto dto) {
        return new City(dto.getZipCode(), dto.getName());
    }

    @Override
    public CityDto toDto(City entity) {
        return new CityDto(entity.getZipCode(), entity.getName());
    }
}