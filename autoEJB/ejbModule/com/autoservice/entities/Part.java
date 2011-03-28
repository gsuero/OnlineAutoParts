package com.autoservice.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_PIEZAS)
@NamedQueries({@NamedQuery(name = "Part.findByCategory", query = "SELECT d FROM Part d where d.category=:categoryid"), //  order by :orderby :orderas
	@NamedQuery(name = "Part.finById", query = "SELECT d FROM Part d where d.piezaId=:partid"),
	@NamedQuery(name = "Part.findAll", query = "SELECT d FROM Part d"),
	@NamedQuery(name = "Part.getCount", query = "SELECT COUNT(d.piezaId) FROM Part d where d.category=:categoryid")})
public class Part implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="piezaid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int piezaId;
	@Column(name="disponible")
	private int disponible;		 	 	 	 	 	 	 
	@Column(name="descripcion")
	private String descripcion;
	@OneToOne 
    @JoinColumn(name="categoryid") 
    private PartCategory category;
	
	public Part(){
		super();
	}

	public int getPiezaId() {
		return piezaId;
	}

	public void setPiezaId(int piezaId) {
		this.piezaId = piezaId;
	}

	public int getDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public PartCategory getCategory() {
		return category;
	}

	public void setCategory(PartCategory category) {
		this.category = category;
	}
	
	public static List<Part> getAvailables(List<Part> parts) {
		
		int count = parts.size();
		for(int a=0; a<count;a++){
			Part parte = parts.get(0);
			if (parte.getDisponible() == 0) 
				parts.remove(0);
		}
		
		return parts;
	}
	public static List<Part> getUnavailables(List<Part> parts) {
		
		int count = parts.size();
		for(int a=0; a<count;a++){
			Part parte = parts.get(0);
			if (parte.getDisponible() == 1) 
				parts.remove(0);
		}
		
		return parts;
	}
}
