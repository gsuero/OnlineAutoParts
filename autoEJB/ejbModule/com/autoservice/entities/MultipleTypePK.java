package com.autoservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MultipleTypePK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String type;		 	 	 	 	 	 	 
	private String description;

    public MultipleTypePK() {
    	super();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
