package com.radomir.drazic.radomirdrazicBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radomir.drazic.radomirdrazicBE.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
