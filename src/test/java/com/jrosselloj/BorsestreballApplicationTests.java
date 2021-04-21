package com.jrosselloj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.enums.RolEnum;
import com.jrosselloj.model.Usuario;
import com.jrosselloj.repository.IUsuarioRepo;

@SpringBootTest
class BorsestreballApplicationTests {
	
	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void createAdminUser() {
		Usuario usuario = new Usuario();
		usuario.setUsuari("admin");
		usuario.setPassword(encoder.encode("1234"));
		usuario.setRol(RolEnum.ADMIN);
		usuario.setIdiomaDefecte(IdiomaEnum.CASTELLA);
		usuarioRepo.save(usuario);
		
		
	}
	
}
