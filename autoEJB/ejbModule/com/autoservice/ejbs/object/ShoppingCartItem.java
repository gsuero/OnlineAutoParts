package com.autoservice.ejbs.object;

public class ShoppingCartItem {
	private int id;
	private int catid;
	private String description;
	private int quantity;
	private boolean toWishList = false;
	private int wishList = 0;
	private String URL = "";
	private ShoppingCartVehicle vehicle; 

	public ShoppingCartItem(int id, int catid, String description, int qty) {
		this.id = id;
		this.catid  = catid;
		this.description = description;
		this.quantity = qty;
	}

	/**
	 * 
	 * @param id
	 * @param catid
	 * @param description
	 * @param qty
	 * @param toWishList Wishlist not yet implemented...
	 * @param wishList Wishlist not yet implemented...
	 */
	public ShoppingCartItem(int id, int catid, String description, int qty, boolean toWishList, int wishList) {
		this.id = id;
		this.catid  = catid;
		this.description = description;
		this.quantity = qty;
		this.toWishList = toWishList;
		this.wishList = wishList;
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

	public boolean isToWishList() {
		return toWishList;
	}

	public void setToWishList(boolean toWishList) {
		this.toWishList = toWishList;
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
/*
	@Override 
	public boolean equals(Object item) {
	    if (this == item) return true;
	    if ( !(item instanceof ShoppingCartItem) ) return false;
	    ShoppingCartItem that = (ShoppingCartItem)item;

	    return
		      EqualsUtil.areEqual(this.id, that.getId()) &&
		      EqualsUtil.areEqual(this.catid, that.getCatid()) &&
		      EqualsUtil.areEqual(this.description, that.getDescription());
	}
*/

	public ShoppingCartVehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(ShoppingCartVehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + catid;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartItem other = (ShoppingCartItem) obj;
		if (catid != other.catid)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [URL=" + URL + ", catid=" + catid
				+ ", description=" + description + ", id=" + id + ", quantity="
				+ quantity + ", toWishList=" + toWishList + ", vehicle="
				+ vehicle.toString() + ", wishList=" + wishList + "]";
	}
	

}
