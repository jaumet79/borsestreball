package com.jrosselloj.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.enums.RolEnum;
import com.jrosselloj.model.Usuario;
import com.jrosselloj.service.IUsuarioService;

@Component
@Scope("view")
public class UsuarioBean extends BaseBean {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	private Usuario usuario = new Usuario();
	
	private List<IdiomaEnum> idiomas = new ArrayList<>(Arrays.asList(IdiomaEnum.values()));
	private List<RolEnum> rols = new ArrayList<>(Arrays.asList(RolEnum.values()));
	
	private List<Usuario> usuaris;
	
	@PostConstruct
	public void init() {
		carregaUsuaris();
	}
	
	private void carregaUsuaris() {
		usuaris = usuarioService.findAll();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void guardarUsuario() {
		usuarioService.registrar(usuario);
		usuario = new Usuario();
		carregaUsuaris();
		
		showInfo("Usuari guardat correctament");
	}
	
	public void cargarModificacionUsuario() {
		//		usuarioService.findByUsuari(null)
	}
	
	public List<IdiomaEnum> getIdiomas() {
		return idiomas;
	}
	
	
	public List<RolEnum> getRols() {
		return rols;
	}
	
	public List<Usuario> getUsuaris() {
		return usuaris;
	}
	
	
	
}
