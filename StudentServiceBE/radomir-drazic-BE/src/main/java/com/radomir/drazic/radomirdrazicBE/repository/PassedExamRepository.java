package com.radomir.drazic.radomirdrazicBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.radomir.drazic.radomirdrazicBE.entity.PassedExam;

@Repository
public interface PassedExamRepository extends JpaRepository<PassedExam, Long>{

	
}
