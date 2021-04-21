package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.model.Bolsa;

public interface IBolsaService {
	
	void saveBolsa(Bolsa bolsa);
	
	List<Bolsa> findAll();
	
	Bolsa findById(Integer id);
	
}
