package com.jrosselloj.enums;

/**
 * Enum tipus de criteri
 * 
 * @author Jaume
 */
public enum TipoCriterio {
	
	REQUISIT("tipo.criterio.requisito"),
	MERIT("tipo.criterio.merito");
	
	private String message;
	
	private TipoCriterio(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
