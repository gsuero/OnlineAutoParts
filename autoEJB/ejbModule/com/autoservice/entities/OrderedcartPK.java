package com.autoservice.entities;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class OrderedcartPK implements Serializable {

    /** identifier field */
    private Integer productid;

    /** identifier field */
    private Integer orderid;

    /** full constructor */
    public OrderedcartPK(Integer productid, Integer orderid) {
        this.productid = productid;
        this.orderid = orderid;
    }

    /** default constructor */
    public OrderedcartPK() {
    }

    public Integer getProductid() {
        return this.productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getOrderid() {
        return this.orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

   

}
