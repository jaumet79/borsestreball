package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, String> {
	
}
