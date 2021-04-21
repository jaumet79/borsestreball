package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Bolsa;

public interface IBolsaRepo extends JpaRepository<Bolsa, Integer> {
	
	
}
