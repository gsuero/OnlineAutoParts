package com.autoservice.ejbs.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;

import com.autoservice.ejbs.object.ShoppingCartEvent;
import com.autoservice.ejbs.object.ShoppingCartItem;
import com.autoservice.ejbs.object.ShoppingCartService;
import com.autoservice.exceptions.InvalidItemException;
import com.autoservice.util.OrderHandler;

@Local
public interface ShoppingCartLocal {
	public void updateItem(ShoppingCartItem item, int event) throws InvalidItemException;
	public void addItem(ShoppingCartItem item) throws InvalidItemException;
	public boolean removeItem (ShoppingCartItem item) throws InvalidItemException;
	public List<ShoppingCartItem> getCartItemsContents();
	public void addService(com.autoservice.ejbs.object.ShoppingCartService service) throws InvalidItemException;
	public void updateService(com.autoservice.ejbs.object.ShoppingCartService service, int event) throws InvalidItemException;
	public boolean removeService(com.autoservice.ejbs.object.ShoppingCartService service) throws InvalidItemException;
	public int getCartCount();
	public List<ShoppingCartService> getCartServices();
}
