package com.autoservices.web.action.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.autoservice.commons.Constants;
import com.autoservice.entities.User;
import com.autoservices.web.services.ServiceItem;
import com.autoservices.web.util.CartUtil;
import com.autoservices.web.util.objects.CartItem;
import com.opensymphony.xwork2.ActionSupport;

public class Cart extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(Cart.class.getName());;
	private HttpServletRequest request;

	private boolean logged = false;
	private User user;
	private List<com.autoservices.web.util.objects.CartVehicle> vehicleList;
	
	public String execute() throws Exception {
		String METHOD_NAME = "execute(): ";
		logger.info(METHOD_NAME + "entering...");
		
		HttpSession session = request.getSession();
		if(session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY) == null) {
			setLogged(false);
			setUser(null);
		} else {
			setLogged(true);
		}
		CartUtil cartUtil = new CartUtil();
		cartUtil.initialize(request);
		
		List<CartItem> items = cartUtil.getShoppingCartItems();
		List<ServiceItem> services = cartUtil.getShoppingCartServices();
		logger.info(METHOD_NAME + "cart content size: " + items.size());
		List<com.autoservices.web.util.objects.CartVehicle> vehicles;
		if ((items != null && items.size() > 0) || (services != null && services.size() > 0)) {
			vehicles = new ArrayList<com.autoservices.web.util.objects.CartVehicle>();
			if (items != null && items.size() > 0) {
				Iterator<CartItem> iter = items.iterator();
				int a = 0;
				while(iter.hasNext()) {
					CartItem item = iter.next();
					logger.info(METHOD_NAME + "got item : " + item.toString());
					if (!vehicles.contains(item.getAttachedVehicle())) {
						com.autoservices.web.util.objects.CartVehicle vehicle = item.getAttachedVehicle();
						logger.info(METHOD_NAME + " got this" + vehicle.toString());
						item.setAttachedVehicle(null);
						vehicle.addItem(item);
						vehicles.add(vehicle);
					} else {
						int pos = cartUtil.getVehiclePositionInList(item.getAttachedVehicle(), vehicles);
						logger.info(METHOD_NAME + "existing car in  position : " + pos);
						if (pos >= 0) {
							item.setAttachedVehicle(null);
							vehicles.get(pos).addItem(item);
						}
					}
					a++;
					logger.info(METHOD_NAME + " going around and around " + a);
					if (a==items.size()) {
						logger.info(METHOD_NAME + "lets break this");
						break;
					}
				}
			}
			
			if (services != null && services.size() > 0) {
				Iterator<ServiceItem> iter = services.iterator();
				int a = 0;
				while(iter.hasNext()) {
					ServiceItem service = iter.next();
					logger.info(METHOD_NAME + "got service : " + service.toString());
					if (!vehicles.contains(service.getVehicle())) {
						com.autoservices.web.util.objects.CartVehicle vehicle = service.getVehicle();
						logger.info(METHOD_NAME + " got this" + vehicle.toString());
						service.setVehicle(null);
						vehicle.addService(service);
						vehicles.add(vehicle);
					} else {
						int pos = cartUtil.getVehiclePositionInList(service.getVehicle(), vehicles);
						logger.info(METHOD_NAME + "existing car in  position : " + pos);
						if (pos >= 0) {
							service.setVehicle(null);
							vehicles.get(pos).addService(service);
						}
					}
					a++;
					logger.info(METHOD_NAME + " going around and around " + a);
					if (a==services.size()) {
						logger.info(METHOD_NAME + "lets break this");
						break;
					}
				}
			}
			logger.info(METHOD_NAME + "got " + vehicles.toString());
			setVehicleList(vehicles);
		}
		return SUCCESS;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}

	public void setVehicleList(List<com.autoservices.web.util.objects.CartVehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public List<com.autoservices.web.util.objects.CartVehicle> getVehicleList() {
		return vehicleList;
	}
}
