package com.autoservice.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_CIUDAD)
@NamedQueries({@NamedQuery(name = "ciudad.findByCountry", query = "SELECT d FROM Ciudad d where d.pais=:pais and d.status=1")})
public class Ciudad implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="ciudadid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ciudadid;
	@Column(name="ciudad")
	private String ciudad;		 	 	 	 	 	 	 
	@Column(name="status")
	private int status;
	
	@ManyToOne 
    @JoinColumn(name="paisid") 
    private Pais pais;
	
	public Ciudad(){
		super();
	}

	public int getCiudadid() {
		return ciudadid;
	}

	public void setCiudadid(int ciudadid) {
		this.ciudadid = ciudadid;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
}
