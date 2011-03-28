package com.autoservices.web.services;

public class OilChangeService extends Service {

	private String tipoAceite;
	private String marcaAceite;
	private int cantidadAceite;
	
	public OilChangeService(String tipoAceite, String marcaAceite,
			int cantidadAceite) {
		super();
		this.tipoAceite = tipoAceite;
		this.marcaAceite = marcaAceite;
		this.cantidadAceite = cantidadAceite;
	}
	public String getTipoAceite() {
		return tipoAceite;
	}
	public void setTipoAceite(String tipoAceite) {
		this.tipoAceite = tipoAceite;
	}
	public String getMarcaAceite() {
		return marcaAceite;
	}
	public void setMarcaAceite(String marcaAceite) {
		this.marcaAceite = marcaAceite;
	}
	public int getCantidadAceite() {
		return cantidadAceite;
	}
	public void setCantidadAceite(int cantidadAceite) {
		this.cantidadAceite = cantidadAceite;
	}
	@Override
	public String toString() {
		return "OilChangeService [cantidadAceite=" + cantidadAceite
				+ ", marcaAceite=" + marcaAceite + ", tipoAceite=" + tipoAceite
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidadAceite;
		result = prime * result
				+ ((marcaAceite == null) ? 0 : marcaAceite.hashCode());
		result = prime * result
				+ ((tipoAceite == null) ? 0 : tipoAceite.hashCode());
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
		OilChangeService other = (OilChangeService) obj;
		if (cantidadAceite != other.cantidadAceite)
			return false;
		if (marcaAceite == null) {
			if (other.marcaAceite != null)
				return false;
		} else if (!marcaAceite.equals(other.marcaAceite))
			return false;
		if (tipoAceite == null) {
			if (other.tipoAceite != null)
				return false;
		} else if (!tipoAceite.equals(other.tipoAceite))
			return false;
		return true;
	}

	
	
}
