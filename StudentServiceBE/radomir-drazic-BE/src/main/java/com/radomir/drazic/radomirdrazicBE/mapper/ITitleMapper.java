package com.radomir.drazic.radomirdrazicBE.mapper;

import org.mapstruct.Mapper;

import com.radomir.drazic.radomirdrazicBE.dto.TitleDto;
import com.radomir.drazic.radomirdrazicBE.entity.Title;

@Mapper(componentModel = "spring")
public interface ITitleMapper {
	
	Title toEntity(TitleDto dto);
	
	TitleDto toDto(Title entity);
}
