package com.autoservice.entities.orders;

import java.util.Date;

public class OrderHeader {
	private int serviceType; 
	private Date dateCreated;
	private long requirement;
	private long client;	
	private OrderDetail detail;

}
