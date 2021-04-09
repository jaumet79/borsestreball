package com.jrosselloj.bean;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class BorsaBean {
	
	//@Autowired
	//private IPersonaService personaService;
	
	private String prueba;
	
	@PostConstruct
	public void init() {
		prueba = "holaaaaa";
	}
	
	
	public String getPrueba() {
		return prueba;
	}
	
	public String getHola() {
		return "holafffffffffffff";
	}
	
	public void registrar() {
		//		personaService.registrar(4, "prova4");
	}
	
	
}
