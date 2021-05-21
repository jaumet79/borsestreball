package com.jrosselloj.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.enums.RolEnum;
import com.jrosselloj.model.Usuario;
import com.jrosselloj.service.IUsuarioService;

/**
 * Classe controladora de la vista de usuaris.xhtml
 * 
 * @author Jaume
 */
@Component
@Scope("view")
public class UsuarioBean extends BaseBean {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	private Usuario usuario = new Usuario();
	
	private List<IdiomaEnum> idiomas = new ArrayList<>(Arrays.asList(IdiomaEnum.values()));
	private List<RolEnum> rols = RolEnum.getRolsPantallaUsuarios();
	
	private List<Usuario> usuaris;
	
	private boolean modoEdicion = false;
	
	// canvi contrasenya
	private Usuario usuarioPwd;
	private String newPwd;
	private String newPwdRep;
	
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
		
		if (!modoEdicion) {
			Usuario usuarioComprobacion = usuarioService.findById(usuario.getUsuari());
			if (usuarioComprobacion != null) {
				showError(getMessage("usuarios.msg.usuario.existe"));
				return;
			}
		}
		
		usuarioService.registrar(usuario, !modoEdicion);
		usuario = new Usuario();
		carregaUsuaris();
		modoEdicion = false;
		
		showInfo(getMessage("usuarios.msg.guardar.ok"));
	}
	
	public void cambiarContrasenya() {
		
		if (newPwd.equals(newPwdRep)) {
			usuarioService.cambiarContrasenya(usuarioPwd.getUsuari(), newPwd);
			showInfo(getMessage("usuarios.msg.cambio.pwd.ok"));
			
			usuarioPwd = null;
			newPwd = null;
			newPwdRep = null;
			
			PrimeFaces.current().executeScript("PF('pwdDialog').hide()");
			
		} else {
			showError(getMessage("usuarios.msg.cambio.pwd.ko"));
			
		}
		
		
	}
	
	public void cargarModificacionUsuario() {
		modoEdicion = true;
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
	
	public boolean isModoEdicion() {
		return modoEdicion;
	}
	
	public boolean isUsuarioConsultor() {
		return usuario != null && RolEnum.CONSULTOR.equals(usuario.getRol());
	}
	
	public Usuario getUsuarioPwd() {
		return usuarioPwd;
	}
	
	public void setUsuarioPwd(Usuario usuarioPwd) {
		this.usuarioPwd = usuarioPwd;
	}
	
	public String getNewPwd() {
		return newPwd;
	}
	
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	
	public String getNewPwdRep() {
		return newPwdRep;
	}
	
	
	public void setNewPwdRep(String newPwdRep) {
		this.newPwdRep = newPwdRep;
	}
	
}
