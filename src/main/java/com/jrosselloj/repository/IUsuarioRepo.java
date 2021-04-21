package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, String> {
	
	Usuario findByUsuari(String usuari);
}
