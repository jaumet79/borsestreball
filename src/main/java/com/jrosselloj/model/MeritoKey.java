package com.jrosselloj.model;

import java.io.Serializable;

public class MeritoKey implements Serializable {
	
	private static final long serialVersionUID = -6101951931919464871L;
	
	private Integer bolsaId;
	
	private String personaDni;
	
	private Integer criterioId;
	
	public MeritoKey() {
		super();
	}
	
	public MeritoKey(Integer bolsaId, String personaDni, Integer criterioId) {
		super();
		this.bolsaId = bolsaId;
		this.personaDni = personaDni;
		this.criterioId = criterioId;
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
	
	
	public Integer getCriterioId() {
		return criterioId;
	}
	
	
	public void setCriterioId(Integer criterioId) {
		this.criterioId = criterioId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolsaId == null) ? 0 : bolsaId.hashCode());
		result = prime * result + ((criterioId == null) ? 0 : criterioId.hashCode());
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
		MeritoKey other = (MeritoKey)obj;
		if (bolsaId == null) {
			if (other.bolsaId != null)
				return false;
		} else if (!bolsaId.equals(other.bolsaId))
			return false;
		if (criterioId == null) {
			if (other.criterioId != null)
				return false;
		} else if (!criterioId.equals(other.criterioId))
			return false;
		if (personaDni == null) {
			if (other.personaDni != null)
				return false;
		} else if (!personaDni.equals(other.personaDni))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}
