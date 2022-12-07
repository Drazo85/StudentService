package com.radomir.drazic.radomirdrazicBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.radomir.drazic.radomirdrazicBE.exceptions.InvalidEntityException;

@SpringBootApplication
public class RadomirDrazicBeApplication {

	public static void main(String[] args) throws InvalidEntityException {
		SpringApplication.run(RadomirDrazicBeApplication.class, args);
	}

}
