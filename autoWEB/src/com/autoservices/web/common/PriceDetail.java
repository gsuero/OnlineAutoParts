package com.autoservices.web.common;

/*
* bean class to set and get the values of PriceDetail class properties
*/


public class PriceDetail {
	
	//identifier fields
    private Integer productid;

   
    private int categoryid;

    
    private String productname;

    
    private String productprice;

   
    private String listprice;
    
    
    private String imagename;

    
    private String quantity;

    
    private String description;

    
    private String briefdisc;

    
//getter and setter methods
	public int getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}


	public String getListprice() {
		return listprice;
	}


	public void setListprice(String listprice) {
		this.listprice = listprice;
	}


	public Integer getProductid() {
		return productid;
	}


	public void setProductid(Integer productid) {
		this.productid = productid;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public String getProductprice() {
		return productprice;
	}


	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}


	public String getBriefdisc() {
		return briefdisc;
	}


	public void setBriefdisc(String briefdisc) {
		this.briefdisc = briefdisc;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImagename() {
		return imagename;
	}


	public void setImagename(String imagename) {
		this.imagename = imagename;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	


	
	
	
	
	
}
