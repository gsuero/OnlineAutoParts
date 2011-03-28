package com.autoservice.entities;

import java.io.Serializable;
import java.util.Date;



public class Order implements Serializable {

    /** identifier field */
    private Integer orderid;

    /** nullable persistent field */
    private Date ordertime;

    /** nullable persistent field */
    private String custfirstname;

    /** nullable persistent field */
    private String custlastname;

    /** nullable persistent field */
    private String custemail;

    /** nullable persistent field */
    private String custcountry;

    /** nullable persistent field */
    private String custzip;

    /** nullable persistent field */
    private String custstate;

    /** nullable persistent field */
    private String custcity;

    /** nullable persistent field */
    private String custaddress;
    /** nullable persistent field */
    private String custphone;
    /** nullable persistent field */
    private Integer custfax;
    
    
    /** nullable persistent field */
    private String creditCardType;
    
    /** nullable persistent field */
    private String cardHolderName;
    
    /** nullable persistent field */
    private String creditCardNumber;
    
    /** nullable persistent field */
    private Date expirationDate;
    
    /** nullable persistent field */
    private String customerNotes;
    
    /** nullable persistent field */
    private Integer cvvNumber;
    
    

    /** full constructor */
    public Order(Integer orderid, Date ordertime,String creditCardType,String cardHolderName,String creditCardNumber,Date expirationDate,String customerNotes,Integer cvvNumber, String custfirstname, String custlastname, String custemail, String custcountry, String custzip, String custstate, String custcity, String custaddress, String custphone, Integer custfax) {
        this.orderid = orderid;
        this.ordertime = ordertime;
        this.custfirstname = custfirstname;
        this.custlastname = custlastname;
        this.custemail = custemail;
        this.custcountry = custcountry;
        this.custzip = custzip;
        this.custstate = custstate;
        this.custcity = custcity;
        this.custaddress = custaddress;
        this.custphone = custphone;
        this.custfax = custfax;
        this.cardHolderName=cardHolderName;
        this.creditCardNumber=creditCardNumber;
        this.creditCardType=creditCardType;
        this.customerNotes=customerNotes;
        this.expirationDate=expirationDate;
        this.cvvNumber=cvvNumber;
        
        
    }

    /** default constructor */
    public Order() {
    }

    /** minimal constructor */
    public Order(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderid() {
        return this.orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Date getOrdertime() {
        return this.ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getCustfirstname() {
        return this.custfirstname;
    }

    public void setCustfirstname(String custfirstname) {
        this.custfirstname = custfirstname;
    }

    public String getCustlastname() {
        return this.custlastname;
    }

    public void setCustlastname(String custlastname) {
        this.custlastname = custlastname;
    }

    public String getCustemail() {
        return this.custemail;
    }

    public void setCustemail(String custemail) {
        this.custemail = custemail;
    }

    public String getCustcountry() {
        return this.custcountry;
    }

    public void setCustcountry(String custcountry) {
        this.custcountry = custcountry;
    }

    public String getCustzip() {
        return this.custzip;
    }

    public void setCustzip(String custzip) {
        this.custzip = custzip;
    }

    public String getCuststate() {
        return this.custstate;
    }

    public void setCuststate(String custstate) {
        this.custstate = custstate;
    }

    public String getCustcity() {
        return this.custcity;
    }

    public void setCustcity(String custcity) {
        this.custcity = custcity;
    }

    public String getCustaddress() {
        return this.custaddress;
    }

    public void setCustaddress(String custaddress) {
        this.custaddress = custaddress;
    }

    public String getCustphone() {
        return this.custphone;
    }

    public void setCustphone(String custphone) {
        this.custphone = custphone;
    }

    public Integer getCustfax() {
        return this.custfax;
    }

    public void setCustfax(Integer custfax) {
        this.custfax = custfax;
    }

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCustomerNotes() {
		return customerNotes;
	}

	public void setCustomerNotes(String customerNotes) {
		this.customerNotes = customerNotes;
	}

	public Integer getCvvNumber() {
		return cvvNumber;
	}

	public void setCvvNumber(Integer cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

   

}
