package com.autoservice.entities.orders;

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
@Table(name=EjbConstants.TABLE_ITEM_PRICES)
@NamedQueries({@NamedQuery(name = "ItemPrice.findByAll", query = "SELECT d FROM ItemPrice d"),
			   @NamedQuery(name = "ItemPrice.findById", query = "SELECT d FROM ItemPrice d where d.itempriceid=:itempriceid")})
public class ItemPrice implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="itempriceid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long itempriceid;
	@Column(name="japanese")
	private double japanese;
	@Column(name="chinese")
	private double chinese;
	@Column(name="american")
	private double usa;
	

	public long getItempriceid() {
		return itempriceid;
	}
	public void setItempriceid(long itempriceid) {
		this.itempriceid = itempriceid;
	}
	public double getJapanese() {
		return japanese;
	}
	public void setJapanese(double japanese) {
		this.japanese = japanese;
	}
	public double getChinese() {
		return chinese;
	}
	public void setChinese(double chinese) {
		this.chinese = chinese;
	}
	public double getUsa() {
		return usa;
	}
	public void setUsa(double usa) {
		this.usa = usa;
	}
}
