package com.autoservice.entities;

import java.io.Serializable;


public class Config implements Serializable {

    
	/** identifier field */
    private Integer id;
	
	/** identifier field */
    private String storename;

    /** identifier field */
    private String storeurl;

    /** identifier field */
    private String ordernoticeemail;

    /** identifier field */
    private String cursymbol;

    /** identifier field */
    private String curcode;

   

    /** identifier field */
    private String aboutustext;

    /** identifier field */
    private String shipdeltext;

    /** full constructor */
    public Config(Integer id,String storename, String storeurl, String ordernoticeemail, String cursymbol, String curcode, String aboutustext, String shipdeltext) {
        this.storename = storename;
        this.storeurl = storeurl;
        this.ordernoticeemail = ordernoticeemail;
        this.cursymbol = cursymbol;
        this.curcode = curcode;
        this.id=id;
        this.aboutustext = aboutustext;
        this.shipdeltext = shipdeltext;
    }

    /** default constructor */
    public Config() {
    }

    public String getStorename() {
        return this.storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStoreurl() {
        return this.storeurl;
    }

    public void setStoreurl(String storeurl) {
        this.storeurl = storeurl;
    }

    public String getOrdernoticeemail() {
        return this.ordernoticeemail;
    }

    public void setOrdernoticeemail(String ordernoticeemail) {
        this.ordernoticeemail = ordernoticeemail;
    }

    public String getCursymbol() {
        return this.cursymbol;
    }

    public void setCursymbol(String cursymbol) {
        this.cursymbol = cursymbol;
    }

    public String getCurcode() {
        return this.curcode;
    }

    public void setCurcode(String curcode) {
        this.curcode = curcode;
    }


    public String getAboutustext() {
        return this.aboutustext;
    }

    public void setAboutustext(String aboutustext) {
        this.aboutustext = aboutustext;
    }

    public String getShipdeltext() {
        return this.shipdeltext;
    }

    public void setShipdeltext(String shipdeltext) {
        this.shipdeltext = shipdeltext;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

   

}
