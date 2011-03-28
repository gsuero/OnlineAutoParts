package com.autoservice.ejbs.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import org.apache.log4j.Logger;

import com.autoservice.ejbs.object.ShoppingCartEvent;
import com.autoservice.ejbs.object.ShoppingCartService;
import com.autoservice.exceptions.InvalidItemException;
import com.autoservice.util.OrderHandler;

@Stateful(name="ShoppingCart", mappedName="ejb/ShoppingCartJNDI", description="Shopping Cart")
public class ShoppingCart implements ShoppingCartLocal {
	static Logger logger = Logger.getLogger(ShoppingCart.class.getName());;
	private List<com.autoservice.ejbs.object.ShoppingCartItem> items = new ArrayList<com.autoservice.ejbs.object.ShoppingCartItem>();;
	private List<com.autoservice.ejbs.object.ShoppingCartService> services = new ArrayList<com.autoservice.ejbs.object.ShoppingCartService>();

	@Resource
	private SessionContext sctx;
	
	public void updateItem(com.autoservice.ejbs.object.ShoppingCartItem item, int event) throws InvalidItemException {
		if (items == null)
			items = new ArrayList<com.autoservice.ejbs.object.ShoppingCartItem>();
		
		if (event == ShoppingCartEvent.ADD) {
			addItem(item);
			return;
		} else if (event == ShoppingCartEvent.UPDATE || event == ShoppingCartEvent.INCREMENT) {
			if (item.getQuantity() == 0) {
				removeItem(item);
				return;				
			}
			Iterator<com.autoservice.ejbs.object.ShoppingCartItem> iter = items.iterator();
			com.autoservice.ejbs.object.ShoppingCartItem currentItem = null;
			boolean found = false;
			while(iter.hasNext()) {
				currentItem = iter.next();
				if (currentItem.equals(item)) {
					iter.remove();
					found = true;
					break;
				}
			}
			if (!found){
				throw new InvalidItemException("No existe tal item");
			}
			if (currentItem != null) {
				if (event == ShoppingCartEvent.INCREMENT) {
					currentItem.setQuantity(currentItem.getQuantity() +  item.getQuantity());
				} else {
					currentItem.setQuantity(item.getQuantity());
					if(item.getVehicle() != null)
						currentItem.setVehicle(item.getVehicle());
				}
				items.add(currentItem);
				return;
			}
		} else if (event == ShoppingCartEvent.REMOVE) {
			removeItem(item);
			return;
		}
	}
	public void addItem(com.autoservice.ejbs.object.ShoppingCartItem item) throws InvalidItemException {
		String METHOD_NAME = "addItem";
		logger.info(METHOD_NAME + " entrando con " + item.toString());
		if (items == null)
			items = new ArrayList<com.autoservice.ejbs.object.ShoppingCartItem>();
		
		if (OrderHandler.isShoppingCartItemValid(item)) {
			if (items.contains(item)) {
				logger.info(METHOD_NAME + " Item ya existía...");
				updateItem(item, ShoppingCartEvent.INCREMENT); 
			} else {
				logger.info(METHOD_NAME + " agregando item...");
				items.add(item);
			}
		} else {
			throw new InvalidItemException("Item invalido.");
		}
		logger.info(METHOD_NAME + " exiting with : " + items.toString());
	}
	public boolean removeItem (com.autoservice.ejbs.object.ShoppingCartItem item) throws InvalidItemException {
		if (items != null) {
			return items.remove(item);
		}
		return true;
	}
	
	public void addService(com.autoservice.ejbs.object.ShoppingCartService service) throws InvalidItemException {
		String METHOD_NAME = "addService()";
		logger.info(METHOD_NAME + " entrando con " + service.toString());
		if (services == null)
			services = new ArrayList<com.autoservice.ejbs.object.ShoppingCartService>();
		
		if (OrderHandler.isShoppingCartServiceValid(service)) {
			if (services.contains(service)) {
				logger.info(METHOD_NAME + " Item ya existía...");
				updateService(service, ShoppingCartEvent.UPDATE); 
			} else {
				logger.info(METHOD_NAME + " agregando item...");
				services.add(service);
			}
		} else {
			throw new InvalidItemException("Item invalido.");
		}
		logger.info(METHOD_NAME + " exiting with : " + services.toString());
	}
	
	public void updateService(com.autoservice.ejbs.object.ShoppingCartService service, int event) throws InvalidItemException {
		if (services == null)
			services = new ArrayList<com.autoservice.ejbs.object.ShoppingCartService>();
		
		if (event == ShoppingCartEvent.ADD) {
			addService(service);
			return;
		} else if (event == ShoppingCartEvent.UPDATE || event == ShoppingCartEvent.INCREMENT) {
			Iterator<com.autoservice.ejbs.object.ShoppingCartService> iter = services.iterator();
			com.autoservice.ejbs.object.ShoppingCartService currentService = null;
			boolean found = false;
			while(iter.hasNext()) {
				currentService = iter.next();
				if (currentService.equals(service)) {
					iter.remove();
					found = true;
					break;
				}
			}
			if (!found){
				throw new InvalidItemException("No existe tal servicio");
			}
			if (currentService != null) {
				services.add(currentService);
				return;
			}
		} else if (event == ShoppingCartEvent.REMOVE) {
			removeService(service);
			return;
		}
	}
	
	public boolean removeService(com.autoservice.ejbs.object.ShoppingCartService service) throws InvalidItemException {
		if (services != null) {
			return services.remove(service);
		}
		return true;
	}
	public int getCartCount() {
		String METHOD_NAME = "getCartCount()";
		logger.info(METHOD_NAME + " entrando ");
		int total = (items != null ? items.size() : 0) + (services != null ? services.size() : 0);
		return total;
	}
	public List<com.autoservice.ejbs.object.ShoppingCartItem> getCartItemsContents() {
		String METHOD_NAME = "getCarContents()";
		logger.info(METHOD_NAME + " entrando ");
		return items;
	}
	public List<ShoppingCartService> getCartServices() {
		String METHOD_NAME = "ShoppingCartService()";
		logger.info(METHOD_NAME + " entrando ");
		return services;
	}
}
