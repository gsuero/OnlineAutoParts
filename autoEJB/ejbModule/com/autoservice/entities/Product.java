package com.autoservice.entities;

import java.io.Serializable;

/** @author Hibernate CodeGenerator */
public class Product implements Serializable {

    /** identifier field */
    private Integer productid;

    /** persistent field */
    private int categoryid;

    /** persistent field */
    private String productname;

    /** persistent field */
    private String productprice;

    /** persistent field */
    private String listprice;

    /** persistent field */
    private String imagename;

    /** persistent field */
    private String quantity;

    /** persistent field */
    private String description;

    /** nullable persistent field */
    private String briefdisc;

    /** full constructor */
    public Product(Integer productid, int categoryid, String productname, String productprice, String listprice, String imagename, String quantity, String description, String briefdisc) {
        this.productid = productid;
        this.categoryid = categoryid;
        this.productname = productname;
        this.productprice = productprice;
        this.listprice = listprice;
        this.imagename = imagename;
        this.quantity = quantity;
        this.description = description;
        this.briefdisc = briefdisc;
    }

    /** default constructor */
    public Product() {
    }

    /** minimal constructor */
    public Product(Integer productid, int categoryid, String productname, String productprice, String listprice, String imagename, String quantity, String description) {
        this.productid = productid;
        this.categoryid = categoryid;
        this.productname = productname;
        this.productprice = productprice;
        this.listprice = listprice;
        this.imagename = imagename;
        this.quantity = quantity;
        this.description = description;
    }

    public Integer getProductid() {
        return this.productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public int getCategoryid() {
        return this.categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getProductname() {
        return this.productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return this.productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getListprice() {
        return this.listprice;
    }

    public void setListprice(String listprice) {
        this.listprice = listprice;
    }

    public String getImagename() {
        return this.imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBriefdisc() {
        return this.briefdisc;
    }

    public void setBriefdisc(String briefdisc) {
        this.briefdisc = briefdisc;
    }

   

}
