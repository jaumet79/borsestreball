package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.model.Bolsa;
import com.jrosselloj.model.Usuario;

public interface IBolsaService {
	
	void saveBolsa(Bolsa bolsa, Usuario usuarioCreador);
	
	List<Bolsa> findAll();
	
	Bolsa findById(Integer id);
	
}
