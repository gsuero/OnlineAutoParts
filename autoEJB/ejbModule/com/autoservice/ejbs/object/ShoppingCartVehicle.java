package com.autoservice.ejbs.object;

public class ShoppingCartVehicle {
	private int id;
	private String description;		 	 	 	 	 	 	 
	private String chassis;
	private String marca;
	private String modelo;
	private int anio;
	
	public ShoppingCartVehicle(String description, String chassis,
			int marca, int modelo, int anio) {
		super();
		this.description = description;
		this.chassis = chassis;
		this.marca = marca+"";
		this.modelo = modelo+"";
		this.anio = anio;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anio;
		result = prime * result + id;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
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
		ShoppingCartVehicle other = (ShoppingCartVehicle) obj;
		if (anio != other.anio)
			return false;
		if (id != other.id)
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShoppingCartVehicle [anio=" + anio + ", chassis=" + chassis
				+ ", description=" + description + ", id=" + id + ", marca="
				+ marca + ", modelo=" + modelo + "]";
	}
	
	
}
