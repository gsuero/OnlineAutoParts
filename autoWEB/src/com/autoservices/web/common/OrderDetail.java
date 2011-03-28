package com.autoservices.web.common;
import java.util.*;
/*
 * bean class to set and get the values of OrderDetail class properties
 */
public class OrderDetail {
	
	/*
	 * identifier fields
	 */
	
    private Integer orderid;

   
    private Date ordertime;

    
    private String custfirstname;

    
    private String custlastname;

   
    private String custemail;
    
    
    private String custcountry;

    
    private String custzip;

    
    private String custstate;

    
    private String custcity;
    
    
    private String custaddress;

    private String custphone;
    
    private String custfax;
    
    private String productname;
    
    private String price;
    
    private String quantity;

    
  //getter and setter methods
    
	public String getCustaddress() {
		return custaddress;
	}

	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}

	public String getCustcity() {
		return custcity;
	}

	public void setCustcity(String custcity) {
		this.custcity = custcity;
	}

	public String getCustcountry() {
		return custcountry;
	}

	public void setCustcountry(String custcountry) {
		this.custcountry = custcountry;
	}

	public String getCustemail() {
		return custemail;
	}

	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}

	public String getCustfax() {
		return custfax;
	}

	public void setCustfax(String custfax) {
		this.custfax = custfax;
	}

	public String getCustfirstname() {
		return custfirstname;
	}

	public void setCustfirstname(String custfirstname) {
		this.custfirstname = custfirstname;
	}

	public String getCustlastname() {
		return custlastname;
	}

	public void setCustlastname(String custlastname) {
		this.custlastname = custlastname;
	}

	public String getCustphone() {
		return custphone;
	}

	public void setCustphone(String custphone) {
		this.custphone = custphone;
	}

	public String getCuststate() {
		return custstate;
	}

	public void setCuststate(String custstate) {
		this.custstate = custstate;
	}

	public String getCustzip() {
		return custzip;
	}

	public void setCustzip(String custzip) {
		this.custzip = custzip;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
    

	
	
	
	
	
}
