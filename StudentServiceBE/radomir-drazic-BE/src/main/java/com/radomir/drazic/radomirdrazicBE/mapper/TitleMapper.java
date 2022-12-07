package com.radomir.drazic.radomirdrazicBE.mapper;

import org.springframework.stereotype.Component;

import com.radomir.drazic.radomirdrazicBE.dto.TitleDto;
import com.radomir.drazic.radomirdrazicBE.entity.Title;

@Component
public class TitleMapper implements GenericMapper<Title, TitleDto>{

	@Override
	public Title toEntity(TitleDto dto) {
		
		return new Title(dto.getTitleId(), dto.getTitle());
	}

	@Override
	public TitleDto toDto(Title entity) {
		return new TitleDto(entity.getTitleId(), entity.getTitle());
	}

}
