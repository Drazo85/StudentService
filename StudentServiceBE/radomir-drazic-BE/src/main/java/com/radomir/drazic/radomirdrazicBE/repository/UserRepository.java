package com.radomir.drazic.radomirdrazicBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radomir.drazic.radomirdrazicBE.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
