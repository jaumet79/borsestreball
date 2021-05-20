package com.jrosselloj.enums;

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
