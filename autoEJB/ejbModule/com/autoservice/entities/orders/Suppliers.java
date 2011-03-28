package com.autoservice.entities.orders;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.autoservice.entities.Suplidor;
import com.autoservice.util.EjbConstants;

@Entity
@Table(name = EjbConstants.TABLE_SUPPLIER_PER_ORDER)
@NamedQueries( {@NamedQuery(name = "Suppliers.findByPartId", query = "SELECT d FROM Suppliers d where d.partid = :partid and d.orderid=:orderid") })
public class Suppliers  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SuppliersPK suppliersPK;
	
	@Column(name="orderid")
	private int orderid;
	
	@Column(name="partid")
	private int partid;
	
	@OneToOne
	@JoinColumn(name="suplidor",referencedColumnName = "suplidorid", nullable = false, updatable = false)
	private Suplidor suplidor;
	
	@OneToOne
	@JoinColumn(name = "price", referencedColumnName="itempriceid", nullable = false, updatable = false)
	private ItemPrice price;
	
	
	public SuppliersPK getSuppliersPK() {
		return suppliersPK;
	}
	public void setSuppliersPK(SuppliersPK suppliersPK) {
		this.suppliersPK = suppliersPK;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getPartid() {
		return partid;
	}
	public void setPartid(int partid) {
		this.partid = partid;
	}
	public Suplidor getSuplidor() {
		return suplidor;
	}
	public void setSuplidor(Suplidor suplidor) {
		this.suplidor = suplidor;
	}
	public ItemPrice getPrice() {
		return price;
	}
	public void setPrice(ItemPrice price) {
		this.price = price;
	}
	
	
}
