package com.autoservice.entities.orders;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.autoservice.entities.Vehicle;
import com.autoservice.util.EjbConstants;

@Entity
@Table(name = EjbConstants.TABLE_ORDER_ITEM) //order_item
@NamedQueries( {@NamedQuery(name = "OrderItem.findByOrderid", query = "SELECT d FROM OrderItem d where d.orderid = :orderid")})
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name="orderid", column=@Column(name="orderid")),
        @AttributeOverride(name="partid", column=@Column(name="partid"))
    })
	private OrderItemPK orderItemPK;
	@Column(name = "orderid")
	private int orderid;
	@Column(name = "partid")
	private int partid;
	@Column(name = "descripcion")
	private String description;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "gainPercentage")
	private double gainPercentage;
	
	public OrderItemPK getOrderItemPK() {
		return orderItemPK;
	}
	public void setOrderItemPK(OrderItemPK orderItemPK) {
		this.orderItemPK = orderItemPK;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getGainPercentage() {
		return gainPercentage;
	}
	public void setGainPercentage(double gainPercentage) {
		this.gainPercentage = gainPercentage;
	}

	
}
