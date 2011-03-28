package com.autoservices.web.util.objects;

import java.util.ArrayList;
import java.util.List;

import com.autoservice.ejbs.object.ShoppingCartVehicle;
import com.autoservice.entities.Vehicle;
import com.autoservice.exceptions.NotExistsException;
import com.autoservices.web.services.ServiceItem;
import com.autoservices.web.util.DatalayerUtil;

public class CartVehicle {
	private int id;
	private String description;		 	 	 	 	 	 	 
	private String chassis;
	private String marca;
	private int brandcode;
	private String modelo;
	private int modelcode;
	private int anio;
	
	/*to be used printing the item*/
	private List<CartItem> items = new ArrayList<CartItem>();
	
	private List<ServiceItem> services = new ArrayList<ServiceItem>();
	
	public CartVehicle(){
		super();
	}

	public CartVehicle(ShoppingCartVehicle vehicle) throws NotExistsException {
		this.id = vehicle.getId();
		this.description = vehicle.getDescription();
		this.chassis = vehicle.getChassis();
		this.brandcode = Integer.parseInt(vehicle.getMarca());
		this.modelcode = Integer.parseInt(vehicle.getModelo());
		this.marca = DatalayerUtil.getMarcaDescription(vehicle.getMarca());
		this.modelo = DatalayerUtil.getModeloDescription(brandcode, modelcode);
		this.anio = vehicle.getAnio();

	}
	public CartVehicle(String description, String chassis, 
			int brandcode, int modelcode, int anio) throws NotExistsException {
		super();
		this.description = description;
		this.chassis = chassis;
		this.marca = DatalayerUtil.getMarcaDescription(brandcode+"");
		this.brandcode = brandcode;
		this.modelo = DatalayerUtil.getModeloDescription(brandcode, modelcode);
		this.modelcode = modelcode;
		this.anio = anio;
	}

	public CartVehicle(int vehicleid) throws NotExistsException {
		super();
		Vehicle veh = DatalayerUtil.getVehicle(vehicleid);
		this.id = veh.getId();
		this.description = veh.getDescription();
		this.chassis = veh.getChassis();
		this.marca = DatalayerUtil.getMarcaDescription(veh.getMarca());
		this.brandcode = Integer.parseInt(veh.getMarca());
		this.modelo = DatalayerUtil.getModeloDescription(Integer.parseInt(veh.getMarca()), Integer.parseInt(veh.getModelo()));
		this.modelcode = Integer.parseInt(veh.getModelo());
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public void addItem(CartItem item) {
		if (!getItems().contains(item)) {
			this.items.add(item);
		}
	}
	public void addService(ServiceItem service) {
		if (!getServices().contains(service)) {
			this.services.add(service);
		}
	}
	public int getBrandcode() {
		return brandcode;
	}
	public void setBrandcode(int brandcode) {
		this.brandcode = brandcode;
	}
	public int getModelcode() {
		return modelcode;
	}
	public void setModelcode(int modelcode) {
		this.modelcode = modelcode;
	}

	public List<ServiceItem> getServices() {
		return services;
	}

	public void setServices(List<ServiceItem> services) {
		this.services = services;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anio;
		result = prime * result + brandcode;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + modelcode;
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
		CartVehicle other = (CartVehicle) obj;
		if (anio != other.anio)
			return false;
		if (brandcode != other.brandcode)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (modelcode != other.modelcode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartVehicle [anio=" + anio + ", brandcode=" + brandcode
				+ ", chassis=" + chassis + ", description=" + description
				+ ", id=" + id + ", items=" + items + ", marca=" + marca
				+ ", modelcode=" + modelcode + ", modelo=" + modelo + "]";
	}
}
