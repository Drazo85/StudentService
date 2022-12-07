package com.radomir.drazic.radomirdrazicBE.service;

import java.util.List;

import com.radomir.drazic.radomirdrazicBE.dto.TitleDto;

public interface TitleService {

	List<TitleDto> findAllTitles();
}
