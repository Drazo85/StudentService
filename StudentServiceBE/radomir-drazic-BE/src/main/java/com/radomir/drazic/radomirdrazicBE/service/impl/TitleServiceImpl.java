package com.radomir.drazic.radomirdrazicBE.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radomir.drazic.radomirdrazicBE.dto.TitleDto;
import com.radomir.drazic.radomirdrazicBE.entity.Title;
import com.radomir.drazic.radomirdrazicBE.mapper.ITitleMapper;
import com.radomir.drazic.radomirdrazicBE.mapper.TitleMapper;
import com.radomir.drazic.radomirdrazicBE.repository.TitleRepository;
import com.radomir.drazic.radomirdrazicBE.service.TitleService;

@Service
public class TitleServiceImpl implements TitleService {

	@Autowired
	private TitleRepository titleRepository;
	@Autowired
	private TitleMapper titleMapper;
//	@Autowired
//	private ITitleMapper iTitleMapper;
	@Override
	public List<TitleDto> findAllTitles() {
		List<Title> titles = titleRepository.findAll();
		return titles.stream().map(titleMapper::toDto).collect(Collectors.toList());
	}
	
	
}
