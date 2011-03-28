package com.autoservices.web.services;

public class TireChangeService extends Service {

	/*private int altura;
	private int anchura;
	private int aro;*/
	private String referencia;
	//private String tipoGoma;
	private String marcaPredilecta;
	private int quantity;
	
	
/*	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getAnchura() {
		return anchura;
	}
	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}
	public int getAro() {
		return aro;
	}
	public void setAro(int aro) {
		this.aro = aro;
	}*/
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}/*
	public String getTipoGoma() {
		return tipoGoma;
	}
	public void setTipoGoma(String tipoGoma) {
		this.tipoGoma = tipoGoma;
	}*/
	public String getMarcaPredilecta() {
		return marcaPredilecta;
	}
	public void setMarcaPredilecta(String marcaPredilecta) {
		this.marcaPredilecta = marcaPredilecta;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "TireChangeService [marcaPredilecta=" + marcaPredilecta
				+ ", quantity=" + quantity + ", referencia=" + referencia + "]";
	}
	public TireChangeService(String referencia, String marcaPredilecta,
			int quantity) {
		super();
		this.referencia = referencia;
		this.marcaPredilecta = marcaPredilecta;
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((marcaPredilecta == null) ? 0 : marcaPredilecta.hashCode());
		result = prime * result + quantity;
		result = prime * result
				+ ((referencia == null) ? 0 : referencia.hashCode());
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
		TireChangeService other = (TireChangeService) obj;
		if (marcaPredilecta == null) {
			if (other.marcaPredilecta != null)
				return false;
		} else if (!marcaPredilecta.equals(other.marcaPredilecta))
			return false;
		if (quantity != other.quantity)
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		return true;
	}
}
