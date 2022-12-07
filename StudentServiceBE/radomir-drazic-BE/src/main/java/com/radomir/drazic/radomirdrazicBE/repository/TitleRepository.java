package com.radomir.drazic.radomirdrazicBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radomir.drazic.radomirdrazicBE.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

}
