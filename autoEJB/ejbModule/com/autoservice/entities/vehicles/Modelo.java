package com.autoservice.entities.vehicles;

import java.io.Serializable;
import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_VEHICULO_MODELO)
@NamedQueries({@NamedQuery(name = "modelo.findByMarca", query = "SELECT d FROM Modelo d where d.marca=:marca and d.status=1"),
			@NamedQuery(name = "modelo.findAll", query = "SELECT d FROM Modelo d where d.status=1"),
			@NamedQuery(name = "modelo.findByModelAndBrand", query = "SELECT d FROM Modelo d where d.modeloid=:modeloid AND d.marca=:marca AND d.status=1")})
public class Modelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="modeloid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int modeloid;
	@Column(name="modelo")
	private String modelo;		 	 	 	 	 	 	 
	@Column(name="status")
	private int status;
	
	@ManyToOne 
    @JoinColumn(name="marcaid") 
    private Marca marca;
	
	public Modelo(){
		super();
	}

	public int getModeloid() {
		return modeloid;
	}

	public void setModeloid(int modeloid) {
		this.modeloid = modeloid;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}
