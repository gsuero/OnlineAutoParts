package com.autoservice.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_MULTIPLE_TYPES)
@NamedQueries({@NamedQuery(name = "MultipleType.findByType", query = "SELECT d FROM MultipleType d where d.type=:type and d.disponible=1 order by d.description DESC"),
			   @NamedQuery(name = "MultipleType.findAll",   query = "select d from MultipleType d")})
public class MultipleType implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name="type", column=@Column(name="type")),
        @AttributeOverride(name="description", column=@Column(name="description"))
    })
	private MultipleTypePK multipleTypePK;
	@Column(name="type")
	private String type;		 	 	 	 	 	 	 
	@Column(name="description")
	private String description;		 	 	 	 	 	 	 
	@Column(name="disponible")
	private int disponible;
	
	public MultipleType(){
		super();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDisponible() {
		return disponible;
	}
	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}
	public MultipleTypePK getMultipleTypePK() {
		return multipleTypePK;
	}
	public void setMultipleTypePK(MultipleTypePK multipleTypePK) {
		this.multipleTypePK = multipleTypePK;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "MultipleType [description=" + description + ", disponible="
				+ disponible + ", multipleTypePK=" + multipleTypePK + ", type="
				+ type + "]";
	}
	
	
	
}
