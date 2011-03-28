package com.autoservice.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name = EjbConstants.TABLE_PAIS)
@NamedQueries( {
		@NamedQuery(name = "Pais.findAll", query = "SELECT d FROM Pais d where d.status=1"),
		@NamedQuery(name = "Pais.findByCode", query = "SELECT d FROM Pais d where d.paisid=:paisid and d.status=1")})
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	public Pais() {
		setCiudades(new ArrayList<Ciudad>());
	}

	@Id
	@Column(name = "paisid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paisid;
	@Column(name = "pais")
	private String pais;
	@Column(name = "status")
	private int status;
	@OneToMany(mappedBy="pais")
	private List<Ciudad> Ciudades;
	
	
	public void addCiudad(Ciudad ciudad) {
		if (!this.getCiudades().contains(ciudad)) {
			this.getCiudades().add(ciudad);
		}
	}

	public void updateCiudad(Ciudad ciudad) {
		if (this.getCiudades() != null) {
			if (getCiudades().contains(ciudad)) {
				for (int a = 0; a < this.getCiudades().size(); a++) {
					Ciudad city = this.getCiudades().get(a);
					if (city.equals(ciudad)) {
						this.getCiudades().add(a, ciudad);
						break;
					}
				}
			}
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPaisid() {
		return paisid;
	}

	public void setPaisid(int paisid) {
		this.paisid = paisid;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Ciudad> getCiudades() {
		return Ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		Ciudades = ciudades;
	}
}
