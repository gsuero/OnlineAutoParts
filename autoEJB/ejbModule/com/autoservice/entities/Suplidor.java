package com.autoservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_SUPLIDOR)
@NamedQueries({@NamedQuery(name = "suplidor.findByAll", query = "SELECT d FROM Suplidor d"),
			   @NamedQuery(name = "suplidor.findById", query = "SELECT d FROM Suplidor d where d.suplidorid=:suplidorid")})
public class Suplidor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="suplidorid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int suplidorid; // id base de datos
	@Column(name="description")
	private String description;
	@Column(name="contact")
	private String contacto; // Nombre persona
	@Column(name="telephone")
	private String telefono; // formato xxx-xxx-xxxx
	@Column(name="ext")
	private String ext; // xxxx|xxx
	

	public int getSuplidorid() {
		return suplidorid;
	}
	public void setSuplidorid(int suplidorid) {
		this.suplidorid = suplidorid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
}
