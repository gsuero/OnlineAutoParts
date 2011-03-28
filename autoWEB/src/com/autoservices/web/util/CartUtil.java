package com.autoservices.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.HttpRequest;
import org.apache.log4j.Logger;

import com.autoservice.commons.Constants;
import com.autoservice.commons.ServiceType;
import com.autoservice.ejbs.business.BusinessHelper;
import com.autoservice.ejbs.business.BusinessHelperLocal;
import com.autoservice.ejbs.cart.ShoppingCart;
import com.autoservice.ejbs.cart.ShoppingCartLocal;
import com.autoservice.ejbs.object.SPCBatteryChangeService;
import com.autoservice.ejbs.object.SPCGlassTintService;
import com.autoservice.ejbs.object.SPCOilChangeService;
import com.autoservice.ejbs.object.SPCTireChangeService;
import com.autoservice.ejbs.object.SPCTuneUpService;
import com.autoservice.ejbs.object.ShoppingCartAddress;
import com.autoservice.ejbs.object.ShoppingCartEvent;
import com.autoservice.ejbs.object.ShoppingCartItem;
import com.autoservice.ejbs.object.ShoppingCartService;
import com.autoservice.ejbs.object.ShoppingCartVehicle;
import com.autoservice.entities.Part;
import com.autoservice.exceptions.InvalidItemException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservices.web.action.services.ServiciosAction;
import com.autoservices.web.services.BatteryChangeService;
import com.autoservices.web.services.GlassTintService;
import com.autoservices.web.services.OilChangeService;
import com.autoservices.web.services.Service;
import com.autoservices.web.services.ServiceAddress;
import com.autoservices.web.services.ServiceItem;
import com.autoservices.web.services.TireChangeService;
import com.autoservices.web.services.TuneUpService;
import com.autoservices.web.util.objects.CartItem;
import com.autoservices.web.util.objects.CartVehicle;

public class CartUtil {
	static Logger logger = Logger.getLogger(CartUtil.class.getName());
	private HttpSession session;
	private ShoppingCartLocal spc;
	public void initialize(HttpServletRequest request) {
		session = request.getSession(true);
		if (session.getAttribute(Constants.GLOBAL_CART_SESSION_KEY) == null) {
			spc = new ShoppingCart();
		} else {
			spc = (ShoppingCart) session.getAttribute(Constants.GLOBAL_CART_SESSION_KEY);
		}
	}
	/**
	 * Doesn't have to call initialize when using this constructor
	 * @param request
	 */
	public CartUtil(HttpServletRequest request) {
		this.initialize(request);
	}
	/**
	 * Must call initialize when using this constructor...
	 */
	public CartUtil() {
		super();
	}
	public void save() {
		session.setAttribute(Constants.GLOBAL_CART_SESSION_KEY, spc);
	}
	
	
	public void addItemToCart(CartItem item) throws InvalidItemException {
		String METHOD_NAME = "addItemToCart()";
		logger.info(METHOD_NAME + " entrando... con " + item.toString());
		BusinessHelperLocal helper = new BusinessHelper();
		
		try {
			Part part = helper.getPart(item.getId()); 
			ShoppingCartItem scItem = new ShoppingCartItem(part.getPiezaId(), part.getCategory().getCategoryId(), part.getDescripcion(), item.getQuantity());
			scItem.setURL(item.getURL()+"?id="+part.getPiezaId());
			scItem.setToWishList(false);
			
			
			CartVehicle veh = item.getAttachedVehicle();
			if (veh != null) {
				ShoppingCartVehicle vehicle = new ShoppingCartVehicle(veh.getDescription(), veh.getChassis(), veh.getBrandcode(), veh.getModelcode(), veh.getAnio());
				scItem.setVehicle(vehicle);
			} else { 
				throw new InvalidItemException("Debe especificar un automóvil");
			}
			logger.info(METHOD_NAME + " agregando: " + scItem.toString());
			spc.addItem(scItem);
		} catch (NotExistsException ex) {
			throw new InvalidItemException("Item inválido");
		}
	}

	private Object getShoppingCartObjectFromObject(ServiceItem service) throws InvalidItemException {
		if (ServiceType.CAMBIO_ACEITE == service.getType()) {
			OilChangeService oldObject = (OilChangeService) service.getObjectService();
			SPCOilChangeService objectService = new SPCOilChangeService(oldObject.getTipoAceite(), oldObject.getMarcaAceite(), oldObject.getCantidadAceite());
			return objectService;
		} else if (ServiceType.TINTADO_CRISTAL == service.getType()) {
			GlassTintService oldObject = (GlassTintService) service.getObjectService();
			SPCGlassTintService objectService = new SPCGlassTintService(oldObject.getGlasses(), oldObject.getOscurity(), oldObject.getQuality());
			return objectService;
		}  else if (ServiceType.CAMBIO_BATERIA == service.getType()) {
			BatteryChangeService oldObject = (BatteryChangeService) service.getObjectService();
			SPCBatteryChangeService objectService = new SPCBatteryChangeService(oldObject.getBatteryBrand(), oldObject.getTipoBateria(), oldObject.getReferencia());
			return objectService;
		} else if (ServiceType.TUNE_UP == service.getType()) {
			TuneUpService oldObject = (TuneUpService) service.getObjectService();
			SPCTuneUpService objectService = new SPCTuneUpService(oldObject.getGeneral());
			return objectService;
		} else if (ServiceType.CAMBIO_GOMAS == service.getType()) {
			TireChangeService oldObject = (TireChangeService) service.getObjectService();
			SPCTireChangeService objectService = new SPCTireChangeService(oldObject.getReferencia(),oldObject.getMarcaPredilecta(), oldObject.getQuantity());
			return objectService;
		} else {
			throw new InvalidItemException("Servicio inválido");
		}
	}
	public static Service getObjectFromShoppingCartObject(ShoppingCartService service) throws InvalidItemException {
		if (ServiceType.CAMBIO_ACEITE == service.getType()) {
			SPCOilChangeService oldObject = (SPCOilChangeService) service.getObjectService();
			OilChangeService objectService = new OilChangeService(oldObject.getTipoAceite(), oldObject.getMarcaAceite(), oldObject.getCantidadAceite());
			return objectService;
		} else if (ServiceType.TINTADO_CRISTAL == service.getType()) {
			SPCGlassTintService oldObject = (SPCGlassTintService) service.getObjectService();
			GlassTintService objectService = new GlassTintService(oldObject.getGlasses(), oldObject.getOscurity(), oldObject.getQuality());
			return objectService;
		}  else if (ServiceType.CAMBIO_BATERIA == service.getType()) {
			SPCBatteryChangeService oldObject = (SPCBatteryChangeService) service.getObjectService();
			BatteryChangeService objectService = new BatteryChangeService(oldObject.getBatteryBrand(), oldObject.getTipoBateria(), oldObject.getReferencia());
			return objectService;
		} else if (ServiceType.TUNE_UP == service.getType()) {
			SPCTuneUpService oldObject = (SPCTuneUpService) service.getObjectService();
			TuneUpService objectService = new TuneUpService();
			objectService.setGeneral(oldObject.getGeneral());
			return objectService;
		} else if (ServiceType.CAMBIO_GOMAS == service.getType()) {
			SPCTireChangeService oldObject = (SPCTireChangeService) service.getObjectService();
			TireChangeService objectService = new TireChangeService(oldObject.getReferencia(),oldObject.getMarcaPredilecta(), oldObject.getQuantity());
			return objectService;
		} else {
			throw new InvalidItemException("Servicio inválido");
		}
	}
	private ShoppingCartService getSPCServiceFromCartService(ServiceItem service) throws InvalidItemException {
		String METHOD_NAME = "getSPCServiceFromCartService()";
		logger.info(METHOD_NAME + " entrando... con " + service.toString());
		ShoppingCartService scService = new ShoppingCartService();
		scService.setStartServiceTime(service.getStartServiceTime());
		scService.setEndServiceTime(service.getEndServiceTime());
		scService.setType(service.getType());
		scService.setObjectService(getShoppingCartObjectFromObject(service));
		scService.setFecha(service.getFecha());
		scService.setHorario(service.getHorario());
		
		ServiceAddress address = service.getAddress();
		if (address != null) {
			ShoppingCartAddress spcAddress = new ShoppingCartAddress(address.getCiudad(), address.getSector(), 
					address.getCalle(), address.getNumero(), address.getEdificio(), address.getEdificio(), address.getCelular(), address.getTelefono(), address.getReferencias());
			scService.setAddress(spcAddress);
		} else {
			throw new InvalidItemException("Debe especificar una dirección a recibir este servicio");
		}
		
		CartVehicle veh = service.getVehicle();
		if (veh != null) {
			ShoppingCartVehicle vehicle = new ShoppingCartVehicle(veh.getDescription(), veh.getChassis(), veh.getBrandcode(), veh.getModelcode(), veh.getAnio());
			scService.setVehicle(vehicle);
		} else { 
			throw new InvalidItemException("Debe especificar un automóvil");
		}	
		logger.info(METHOD_NAME + " saliendo con: " + scService.toString());
		return scService;
	}
	public void addServiceToCart(ServiceItem service) throws InvalidItemException {
		String METHOD_NAME = "addServiceToCart()";
		logger.info(METHOD_NAME + " entrando... con " + service.toString());
		try {
			spc.addService(getSPCServiceFromCartService(service));
		} catch (InvalidItemException ex) {
			throw new InvalidItemException("Item inválido");
		}
	}
	
	public void removeServiceFromCart(ServiceItem service) throws InvalidItemException {
		String METHOD_NAME = "removeServiceToCart()";
		logger.info(METHOD_NAME + " entrando... con " + service.toString());
		try {
			spc.removeService(getSPCServiceFromCartService(service));
		} catch (InvalidItemException ex) {
			throw new InvalidItemException("Item inválido");
		}
	}

	public void updateServiceToCart(ServiceItem service) throws InvalidItemException {
		String METHOD_NAME = "updateServiceToCart()";
		logger.info(METHOD_NAME + " entrando... con " + service.toString());
		try {
			spc.updateService(getSPCServiceFromCartService(service), ShoppingCartEvent.UPDATE);
		} catch (InvalidItemException ex) {
			throw new InvalidItemException("Item inválido");
		}
	}
	
	public void removeItemFromCart(CartItem item) throws InvalidItemException {
		BusinessHelperLocal helper = new BusinessHelper();
		try {
			Part part = helper.getPart(item.getId()); 
			ShoppingCartItem scItem = new ShoppingCartItem(part.getPiezaId(), part.getCategory().getCategoryId(), part.getDescripcion(), item.getQuantity());
			
			CartVehicle veh = item.getAttachedVehicle();
			if (veh != null) {
				scItem.setVehicle(new ShoppingCartVehicle(veh.getDescription(),veh.getChassis(),veh.getBrandcode(),veh.getModelcode(),veh.getAnio()));
			}
			spc.removeItem(scItem);
		} catch (NotExistsException ex) {
			throw new InvalidItemException("Item inválido");
		}
	}
	
	public void updateCartItem(CartItem item) throws InvalidItemException {
		BusinessHelperLocal helper = new BusinessHelper();
		try {
			Part part = helper.getPart(item.getId()); 
			ShoppingCartItem scItem = new ShoppingCartItem(part.getPiezaId(), part.getCategory().getCategoryId(), part.getDescripcion(), item.getQuantity());
			scItem.setURL(item.getURL()+"?id="+part.getPiezaId());
			scItem.setToWishList(false);
			
			CartVehicle veh = item.getAttachedVehicle();
			if (veh != null) {
				ShoppingCartVehicle vehicle = new ShoppingCartVehicle(veh.getDescription(), veh.getChassis(), veh.getBrandcode(), veh.getModelcode(), veh.getAnio());
				scItem.setVehicle(vehicle);
			}
			spc.updateItem(scItem, ShoppingCartEvent.UPDATE);
		} catch (NotExistsException ex) {
			throw new InvalidItemException("Item inválido");
		}
	}
	
	public List<CartItem> getShoppingCartItems() {
		List<ShoppingCartItem> list = spc.getCartItemsContents();
		List<CartItem> items = new ArrayList<CartItem>();
		Iterator<ShoppingCartItem> iter = list.iterator();
		while (iter.hasNext()) {
			ShoppingCartItem item = iter.next();
			try {
				items.add(new CartItem(item));
			} catch (InvalidItemException e) {
				continue;
			}
		}
		return items;
	}

	public List<ServiceItem> getShoppingCartServices() {
		List<ShoppingCartService> list = spc.getCartServices();
		List<ServiceItem> services = new ArrayList<ServiceItem>();
		Iterator<ShoppingCartService> iter = list.iterator();
		while (iter.hasNext()) {
			ShoppingCartService service = iter.next();
			try {
				services.add(new ServiceItem(service));
			} catch (InvalidItemException e) {
				continue;
			}
		}
		return services;
	}
	
	public int getShoppingCartCount() {
		return spc.getCartCount();
	}
	
	public int getVehiclePositionInList(CartVehicle vehicle, List<CartVehicle> list) {
		int position = 0;
		Iterator<CartVehicle> iter = list.iterator();
		while(iter.hasNext()) {
			CartVehicle veh = iter.next();
			if (vehicle.equals(veh)) {
				return position;
			}
			position++;
		}
		return -1;
	}

}
