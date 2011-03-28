package com.autoservice.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_VEHICLES)
@NamedQueries({@NamedQuery(name = "vehicles.findByUserId",   query = "SELECT d FROM Vehicles d where d.userid=:userid")})
public class Vehicles implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id")
	private int id;
	@Column(name="userid")
	private int userId;		 	 	 	 	 	 	 
	@Column(name="descripcion")
	private String description;		 	 	 	 	 	 	 
	@Column(name="chassis")
	private String chassis;
	@Column(name="marca")
	private String marca;
	@Column(name="modelo")
	private String modelo;
	@Column(name="anio")
	private int anio;
	
	public Vehicles() {
		super();
	}

	public Vehicles(int userid, String desc, String chassis, String marca, String modelo, int anio) {
		setUserId(userid);
		setDescription(desc);
		setChassis(chassis);
		setMarca(marca);
		setModelo(modelo);
		setAnio(anio);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
}
