package com.autoservice.entities;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class Orderedcart implements Serializable {

    /** identifier field */
    private Integer productid;
    
    /** identifier field */
    private Integer orderid;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private Float price;

    /** nullable persistent field */
    private Integer quantity;

    /** full constructor */
    public Orderedcart(Integer productid,Integer orderid, String name, Float price, Integer quantity) {
        this.productid = productid;
        this.orderid = orderid;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /** default constructor */
    public Orderedcart() {
    }

    /** minimal constructor */
    public Orderedcart(Integer productid,Integer orderid) {
        this.productid = productid;
        this.orderid = orderid;
    }

   

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

   

}
