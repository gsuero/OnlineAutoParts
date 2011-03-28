package com.autoservices.web.util.objects;

import java.util.HashMap;

import com.autoservice.ejbs.object.ShoppingCartItem;
import com.autoservice.entities.Vehicle;
import com.autoservice.exceptions.InvalidItemException;
import com.autoservice.exceptions.NotExistsException;

public class CartItem {
	private int id;
	private int catid;
	private String description;
	private int quantity;
	private int wishList;
	private CartVehicle attachedVehicle;
	private String URL;
	
	public CartItem(int id, int catid, int quantity,
			int wishList, CartVehicle attachedVehicle, String uRL) {
		super();
		this.id = id;
		this.catid = catid;
		this.quantity = quantity;
		this.wishList = wishList;
		this.attachedVehicle = attachedVehicle;
		URL = uRL;
	}

	public CartItem(int catid, String description, int quantity, int wishList,
			CartVehicle attachedVehicle, String uRL) {
		super();
		this.catid = catid;
		this.description = description;
		this.quantity = quantity;
		this.wishList = wishList;
		this.attachedVehicle = attachedVehicle;
		URL = uRL;
	}

	public CartItem(HashMap<String, String> map) {
		this.load(map);
	}
	
	public CartItem(ShoppingCartItem item) throws InvalidItemException {
		try {
			load(item);
		} catch (NotExistsException e) {
			e.printStackTrace();
			throw new InvalidItemException("Vehiculo invalido");
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getWishList() {
		return wishList;
	}
	public void setWishList(int wishList) {
		this.wishList = wishList;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}

	public void load(HashMap<String, String> map) {
		String id = map.get("ID");
		String catid = map.get("CATEGORY");
		String desc = map.get("DESCRIPTION");
		String qty = map.get("QUANTITY");
		String wishl = map.get("WISHLIST");
		String url = map.get("URL");
		if (id != null && id.length() > 0) 
			this.setId(Integer.parseInt(id));
	
		if (catid != null && catid.length() > 0) 
			this.setCatid(Integer.parseInt(catid));
		
		this.setDescription(desc);
		this.setURL(url);
		
		if (qty != null && qty.length() > 0) 
			this.setQuantity(Integer.parseInt(qty));
		
		if (wishl != null && wishl.length() > 0) 
			this.setWishList(Integer.parseInt(wishl));
	}
	
	public void load(ShoppingCartItem item) throws NotExistsException {
		String id = item.getId()+"";
		String catid = item.getCatid()+"";
		String desc = item.getDescription();
		String qty = item.getQuantity()+"";
		String wishl = item.getWishList() + "";
		String url = item.getURL()+"";
		CartVehicle vehicle = new CartVehicle(item.getVehicle());
		if (id != null && id.length() > 0) 
			this.setId(Integer.parseInt(id));
	
		if (catid != null && catid.length() > 0) 
			this.setCatid(Integer.parseInt(catid));
		
		this.setDescription(desc);
		this.setURL(url);
		
		if (qty != null && qty.length() > 0) 
			this.setQuantity(Integer.parseInt(qty));
		
		if (wishl != null && wishl.length() > 0) 
			this.setWishList(Integer.parseInt(wishl));
		
		this.attachedVehicle = vehicle;
	}
	public void setAttachedVehicle(int vehicleid) {
		try {
			this.attachedVehicle = new CartVehicle(vehicleid);
		} catch (NotExistsException e) {
			e.printStackTrace();
		}
	}
	public void setAttachedVehicle(String description, String chassis, int marcaCode, int modeloCode, int anio) throws NotExistsException {
		this.attachedVehicle =  new CartVehicle(description, chassis, marcaCode, modeloCode, anio); // new CartVehicle(description,chassis,marcaCode,modeloCode,anio);
	}
	public CartVehicle getAttachedVehicle() {
		return attachedVehicle;
	}

	public void setAttachedVehicle(CartVehicle attachedVehicle) {
		this.attachedVehicle = attachedVehicle;
	}

	@Override
	public String toString() {
		return "CartItem [URL=" + URL + ", attachedVehicle=" + attachedVehicle
				+ ", catid=" + catid + ", description=" + description + ", id="
				+ id + ", quantity=" + quantity + ", wishList=" + wishList
				+ "]";
	}
	
}
