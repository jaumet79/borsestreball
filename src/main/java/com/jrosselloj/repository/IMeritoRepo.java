package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Merito;
import com.jrosselloj.model.MeritoKey;

public interface IMeritoRepo extends JpaRepository<Merito, MeritoKey> {
	
	
}
