package com.autoservices.web.common;

import java.io.Serializable;

/**
 * A category product. 
 * 
 */
public class Products implements Comparable,Serializable {		
	private String id;
	private String name;
	private String description;
	
	/**
	 * Is the subject active ?
	 */
	private boolean active;	
	
	/**
	 * Priority of the subject.
	 */
	private int priority;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean monitor) {
		this.active = monitor;
	}

	public String getDescription() {
		return description;
	}
	
	public String getId() {
		return id;
	}		

	public String getName() {
		return name;
	}

	public void setDescription(String string) {
		description = string;
	}
	
	void setId(String string) {
		id = string;
	}

	public void setName(String string) {
		name = string;
	}		
	
	public int compareTo(Object object) {
		Products s = (Products) object;
		if (name==null && s.getName()==null) {
			return 0;
		} else if (name!=null && s.getName()==null) {
			return -1;
		} else if (name==null && s.getName()!=null) {
			return 1;
		} else {
			return name.compareTo(s.getName());
		}
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
