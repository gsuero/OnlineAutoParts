package com.autoservice.entities.vehicles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name = EjbConstants.TABLE_VEHICULO_MARCA)
@NamedQueries( {
		@NamedQuery(name = "Marca.findAll", query = "SELECT d FROM Marca d where d.status=1"),
		@NamedQuery(name = "Marca.findByCode", query = "SELECT d FROM Marca d where d.marcaid=:marcaid and d.status=1")})
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	public Marca() {
		setModelos(new ArrayList<Modelo>());
	}

	@Id
	@Column(name = "marcaid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int marcaid;
	@Column(name = "marca")
	private String marca;
	@Column(name = "status")
	private int status;
	@OneToMany(mappedBy="marca")
	private List<Modelo> modelos;
	
	
	public void addModelo(Modelo modelo) {
		if (!this.getModelos().contains(modelo)) {
			this.getModelos().add(modelo);
		}
	}

	public void updateModelo(Modelo modelo) {
		if (this.getModelos() != null) {
			if (getModelos().contains(modelo)) {
				for (int a = 0; a < this.getModelos().size(); a++) {
					Modelo city = this.getModelos().get(a);
					if (city.equals(modelo)) {
						this.getModelos().add(a, modelo);
						break;
					}
				}
			}
		}
	}

	public int getMarcaid() {
		return marcaid;
	}

	public void setMarcaid(int marcaid) {
		this.marcaid = marcaid;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelo) {
		this.modelos = modelo;
	}
}
