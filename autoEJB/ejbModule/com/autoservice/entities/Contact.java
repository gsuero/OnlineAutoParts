package com.autoservice.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_CONTACTS)
@NamedQueries({@NamedQuery(name = "contacts.findAll", query = "SELECT d FROM Contacts d where d.status='1'"),
			   @NamedQuery(name = "contacts.findByUserId",   query = "SELECT d FROM Contacts d where d.userid=:userid")})
public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id")
	private int id;
	@Column(name="userid")
	private int userId;		 	 	 	 	 	 	 
	@Column(name="pais")
	private String pais;		 	 	 	 	 	 	 
	@Column(name="ciudad")
	private String ciudad;
	@Column(name="sector")
	private String sector;
	@Column(name="calle")
	private String calle;
	@Column(name="numero")
	private String numero;
	@Column(name="apto")
	private String apto;
	@Column(name="telefono")
	private String telefono;
	@Column(name="celular")
	private String celular;
	@Column(name="status")
	private int status;
	
	public Contact(){
		super();
	}
	public Contact(int userId, String pais, String ciudad, String sector, String calle, String numero, String apto, String telefono, String celular) {
		setUserId(userId);
		setPais(pais);
		setCiudad(ciudad);
		setSector(sector);
		setCalle(calle);
		setNumero(numero);
		setApto(apto);
		setTelefono(telefono);
		setCelular(celular);
		setStatus(1);
	}
	
	/*
	@Override
	public String toString() {
		return "UserType:" + getType() + " [desc: " + getDesc() + "\nadmin: "
				+ getAdmin() + "\nstatus: " + getStatus()+ "]";
	}
	*/
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




	public String getPais() {
		return pais;
	}




	public void setPais(String pais) {
		this.pais = pais;
	}




	public String getCiudad() {
		return ciudad;
	}




	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}




	public String getSector() {
		return sector;
	}




	public void setSector(String sector) {
		this.sector = sector;
	}




	public String getCalle() {
		return calle;
	}




	public void setCalle(String calle) {
		this.calle = calle;
	}




	public String getNumero() {
		return numero;
	}




	public void setNumero(String numero) {
		this.numero = numero;
	}




	public String getApto() {
		return apto;
	}




	public void setApto(String apto) {
		this.apto = apto;
	}




	public String getTelefono() {
		return telefono;
	}




	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public String getCelular() {
		return celular;
	}




	public void setCelular(String celular) {
		this.celular = celular;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}
}
