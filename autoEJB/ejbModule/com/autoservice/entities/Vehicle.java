package com.autoservice.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_VEHICLES)
@NamedQueries({@NamedQuery(name = "vehicle.findAll",   query = "SELECT d FROM Vehicle d"),
				@NamedQuery(name = "vehicle.findByChassis",   query = "SELECT d FROM Vehicle d where d.chassis=:chassis"),
				@NamedQuery(name = "vehicle.findById",   query = "SELECT d FROM Vehicle d where d.id=:vehicleid")})
public class Vehicle implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="vehicleid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
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
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(
            name="UserVehicleMap",
            joinColumns = {@JoinColumn(name="vehicleid",nullable=false)},
           inverseJoinColumns = {@JoinColumn(name="userid",nullable=false)})
	private User user;
	
	public Vehicle() {
		super();
	}
	@Override
	public String toString(){
		return "\n   Vehicle:" + getId() + " [\ndescripcin: " + getDescription() + "\nchassis: "
		+ getChassis() + "\nmarca: " + getMarca() +  "\nmodelo: "+ getModelo() + "" +
		"\nanio: " + getAnio() + "\n]";
	}

	public Vehicle(String desc, String chassis, String marca, String modelo, int anio) {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public boolean equals(Object object) {
		
		if (this == object) return true;
		
		if (object instanceof Vehicle) {
			Vehicle veh = (Vehicle) object;
			return (this.chassis.equalsIgnoreCase(veh.getChassis()) && this.description.equalsIgnoreCase(veh.getDescription())
					&& this.anio == veh.getAnio()
					&& this.modelo.equalsIgnoreCase(veh.getModelo())
					&& this.marca.equalsIgnoreCase(veh.getMarca())); 
		}
		return false;
	}
}
