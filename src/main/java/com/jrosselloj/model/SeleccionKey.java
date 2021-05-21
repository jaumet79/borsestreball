package com.jrosselloj.model;

import java.io.Serializable;

/**
 * Clau primaria entitat selecció
 * 
 * @author Jaume
 */
public class SeleccionKey implements Serializable {
	
	private static final long serialVersionUID = 5106631491709187283L;
	
	private Integer bolsaId;
	
	private String personaDni;
	
	
	public SeleccionKey() {
		super();
	}
	
	public SeleccionKey(Integer bolsaId, String personaDni) {
		super();
		this.bolsaId = bolsaId;
		this.personaDni = personaDni;
	}
	
	
	public Integer getBolsaId() {
		return bolsaId;
	}
	
	public void setBolsaId(Integer bolsaId) {
		this.bolsaId = bolsaId;
	}
	
	public String getPersonaDni() {
		return personaDni;
	}
	
	public void setPersonaDni(String personaDni) {
		this.personaDni = personaDni;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolsaId == null) ? 0 : bolsaId.hashCode());
		result = prime * result + ((personaDni == null) ? 0 : personaDni.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeleccionKey other = (SeleccionKey)obj;
		if (bolsaId == null) {
			if (other.bolsaId != null)
				return false;
		} else if (!bolsaId.equals(other.bolsaId))
			return false;
		if (personaDni == null) {
			if (other.personaDni != null)
				return false;
		} else if (!personaDni.equals(other.personaDni))
			return false;
		return true;
	}
	
}
