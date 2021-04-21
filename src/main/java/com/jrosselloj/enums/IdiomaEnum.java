package com.jrosselloj.enums;

import java.util.Locale;

public enum IdiomaEnum {
	
	CATALA("Català", new Locale("ca", "ES")), //ca-ES
	CASTELLA("Castellà", new Locale("es", "ES")); //es-ES
	
	private String descripcion;
	private Locale locale;
	
	private IdiomaEnum(String descripcion, Locale locale) {
		this.descripcion = descripcion;
		this.locale = locale;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public static IdiomaEnum getIdiomaEnumByLocale(Locale locale) {
		for (IdiomaEnum idiomaEnum : IdiomaEnum.values()) {
			if (idiomaEnum.getLocale().equals(locale)) {
				return idiomaEnum;
			}
		}
		return null;
	}
	
}
