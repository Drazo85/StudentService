package com.radomir.drazic.radomirdrazicBE.mapper;

import com.radomir.drazic.radomirdrazicBE.dto.Dto;
import com.radomir.drazic.radomirdrazicBE.entity.Entity;

public interface GenericMapper<E extends Entity, D extends Dto> {

    E toEntity(D dto);

    D toDto(E entity);
}
