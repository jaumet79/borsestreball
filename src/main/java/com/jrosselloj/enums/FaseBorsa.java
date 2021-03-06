package com.jrosselloj.enums;


/**
 * Fase de la borsa
 * 
 * @author Jaume
 */
public enum FaseBorsa {
	
	SOLICITUD("fase.solicitud"),
	ESMENA("fase.subsanacion"),
	MERITS("fase.meritos"),
	FINALITZADA("fase.finalizada"),
	TANCADA("fase.cerrada");
	
	private String message;
	
	private FaseBorsa(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
}
