package com.autoservice.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_HOLIDAYS)
@NamedQueries({@NamedQuery(name = "Holiday.findByYear", query = "select d from Holiday d where d.year=:year")})
public class Holiday implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name="day", column=@Column(name="day")),
        @AttributeOverride(name="month", column=@Column(name="month")),
        @AttributeOverride(name="year", column=@Column(name="year"))
    })
	private HolidayPK holiDayPK;
	@Column(name="day")
	private int day;		 	 	 	 	 	 	 
	@Column(name="month")
	private int month;
	@Column(name="year")
	private int year;
	@Column(name="celebrates")
	private String celebrates;
	
	public Holiday(){
		super();
	}

	public HolidayPK getHoliDayPK() {
		return holiDayPK;
	}

	public void setHoliDayPK(HolidayPK holiDayPK) {
		this.holiDayPK = holiDayPK;
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

	public String getCelebrates() {
		return celebrates;
	}

	public void setCelebrates(String celebrates) {
		this.celebrates = celebrates;
	}
	
	
}
