package com.radomir.drazic.radomirdrazicBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radomir.drazic.radomirdrazicBE.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
