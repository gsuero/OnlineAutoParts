package com.autoservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/** @author Hibernate CodeGenerator */
@Embeddable
public class HolidayPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int day;		 	 	 	 	 	 	 
	private int month;
	private int year;

    public HolidayPK() {
    }

	public HolidayPK(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
    
}
