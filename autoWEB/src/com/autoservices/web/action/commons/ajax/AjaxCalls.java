package com.autoservices.web.action.commons.ajax;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.autoservice.commons.Constants;
import com.autoservice.commons.ServiceType;
import com.autoservice.ejbs.business.BusinessHelper;
import com.autoservice.ejbs.business.BusinessHelperLocal;
import com.autoservice.entities.Part;
import com.autoservice.entities.vehicles.Modelo;
import com.autoservice.exceptions.InvalidItemException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservices.web.services.BatteryChangeService;
import com.autoservices.web.services.GlassTintService;
import com.autoservices.web.services.OilChangeService;
import com.autoservices.web.services.ServiceAddress;
import com.autoservices.web.services.ServiceItem;
import com.autoservices.web.services.TireChangeService;
import com.autoservices.web.services.TuneUpService;
import com.autoservices.web.util.CartUtil;
import com.autoservices.web.util.DatalayerUtil;
import com.autoservices.web.util.objects.CartItem;
import com.autoservices.web.util.objects.CartVehicle;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class AjaxCalls extends ActionSupport implements ServletResponseAware,ServletRequestAware, ServletContextAware  {
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;

	static Logger logger = Logger.getLogger(AjaxCalls.class.getName());
	
	public void getParts() {
		String catid = (request.getParameter("categoryid")+"").trim();
		if (catid != null && catid.length() > 0) {
			try {
				int categoryid= Integer.parseInt(catid);
                StringBuffer str = new StringBuffer();
                Gson gson = new Gson();
                List<Part> parts = DatalayerUtil.getPartsByCategory(categoryid, 0, 0, null, null); 
                	
                str.append(gson.toJson(parts));
                
                response.setContentType("application/json");
                response.setCharacterEncoding("ISO-8859-1");
        		response.setContentLength(str.length());
        		response.getOutputStream().print(str.toString());
        		response.getOutputStream().flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public void getCartCount() {
		try {
			CartUtil cartUtil = new CartUtil(getRequest());
			int count = cartUtil.getShoppingCartCount();
			request.getSession(true).setAttribute(Constants.GLOBAL_CART_COUNT_SESSION_KEY, count);
			response.setContentType("text/plain");
			response.setContentLength((count+"").length());
			response.getOutputStream().print((count+""));
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public String cartAction() {
		String METHOD_NAME = "cartAction()";
		//operation=add&type=parts&descripcion="+descripcion+"&marca="+marca+"&chassis="+chassis+"&modelo="+modelo+"&anio="+anio+"&cat="+cat+"&part="+part+"&quantity="+quantity
		String operation = (request.getParameter("operation")+"").trim();
		String type = (request.getParameter("type")+"").trim();
		logger.info(METHOD_NAME + " : Entrando con type : " + type + " operation : " +operation);
		CartUtil cartUtil = new CartUtil(getRequest());
		try {
			String descripcion = (request.getParameter("descripcion")+"").trim();
			String chassis = (request.getParameter("chassis")+"").trim();
			String marca = (request.getParameter("marca")+"").trim();
			String modelo = (request.getParameter("modelo")+"").trim();
			String anio = (request.getParameter("anio")+"").trim();
			int anioInt = Integer.parseInt(anio);
			CartVehicle vehicle = new CartVehicle(descripcion,chassis, Integer.parseInt(marca), Integer.parseInt(modelo), anioInt);
			logger.info(METHOD_NAME + " : Vehiculo obtenido : " + vehicle.toString());
			if ("parts".equalsIgnoreCase(type)) {
				String catid = (request.getParameter("cat")+"").trim();
				String partid = (request.getParameter("part")+"").trim();
				String quantity = (request.getParameter("quantity")+"").trim();
				
				
				int partidInt = Integer.parseInt(partid);
				
				int quantityInt = 0;
				if (quantity.length() > 0 && !quantity.equalsIgnoreCase("null")) // opcionales ya que no es necesario borrand un item
					quantityInt = Integer.parseInt(quantity);
				int catidInt = 0;
				if (catid.length() > 0 && !catid.equalsIgnoreCase("null"))  // opcionales ya que no es necesario borrand un item
					catidInt = Integer.parseInt(catid);
				
				CartItem item = new CartItem(partidInt, catidInt, quantityInt,0,vehicle,"/parts/ViewPart.do");
				if ("add".equalsIgnoreCase(operation)) {
					try {
						logger.info(METHOD_NAME + "operation: add --> trabajando con " + item.toString());
						cartUtil.addItemToCart(item);
					} catch (InvalidItemException e) {
						e.printStackTrace();
						return ERROR;
					}
				} else if ("remove".equalsIgnoreCase(operation)) {
					try {
						logger.info(METHOD_NAME + "operation: remove --> trabajando con " + item.toString());
						cartUtil.removeItemFromCart(item);
					} catch (InvalidItemException e) {
						e.printStackTrace();
						return ERROR;
					}					
					
				}  else if ("update".equalsIgnoreCase(operation)) {
					try {
						logger.info(METHOD_NAME + "operation: update --> trabajando con " + item.toString());
						cartUtil.updateCartItem(item);
					} catch (InvalidItemException e) {
						e.printStackTrace();
						return ERROR;
					}					
					
				}
				cartUtil.save();
			} else if ("services".equalsIgnoreCase(type)) { // SERVICIOS
				logger.info(METHOD_NAME + " : En servicios...");
				int serviceType = Integer.parseInt((request.getParameter("serviceType")+"").trim().length() > 0 ? (request.getParameter("serviceType")+"").trim() : "0");
				logger.info(METHOD_NAME + " : Service Type : " + serviceType);
				boolean selectedService = true;
				ServiceItem item = null;
				String apartamento = (request.getParameter("apartamento")+"").trim();
				String calle = (request.getParameter("calle")+"").trim();
				String celular = (request.getParameter("celular")+"").trim();
				String telefono = (request.getParameter("telefono")+"").trim();
				String ciudad = (request.getParameter("ciudad")+"").trim(); // codigo ciudad
				String edificio = (request.getParameter("edificio")+"").trim();
				String numero = (request.getParameter("numero")+"").trim();
				String referencias = (request.getParameter("referencias")+"").trim();
				String sector = (request.getParameter("sector")+"").trim();
				String fecha = (request.getParameter("fecha")+"").trim();
				String horario = (request.getParameter("horario")+"").trim();
				String startServiceTime = (request.getParameter("startServiceTime")+"").trim();
				
				// TODO validar si la fecha y el horario estan disponibles para el servicio...
				Date fechaServicio = null;
				logger.info(METHOD_NAME + " : Validando fecha? " + fecha + " horario : " + horario);
				if (fecha != null && fecha.length() > 0 && horario != null && horario.length() > 0) 
					fechaServicio = new Date(fecha + " " + horario);
				else 
					fechaServicio = new Date();
				
				ServiceAddress serviceAddress = new ServiceAddress(ciudad, sector, calle, numero, edificio, apartamento, celular, telefono, referencias);
				logger.info(METHOD_NAME + " : Direccion obtenida : " + serviceAddress.toString());
				
				// TODO ver por que no esta borrando el tintato.. talvez quitar el string[] del equal y hashcode
				if (serviceType == ServiceType.CAMBIO_ACEITE) {
					String oilType =  (request.getParameter("oilType")+"").trim();
					String oilBrand =  (request.getParameter("marcaAceite")+"").trim();
					int oilQuantity =  Integer.parseInt((request.getParameter("cantidadAceite")+"").trim());
					OilChangeService service = new OilChangeService(oilType, oilBrand, oilQuantity);
					item = new ServiceItem(ServiceType.CAMBIO_ACEITE, service, vehicle, fechaServicio, serviceAddress);
				} else if(serviceType == ServiceType.TINTADO_CRISTAL) {
					String tipoTintado =  (request.getParameter("tipoTintado")+"").trim();
					String oscuridad =  (request.getParameter("oscuridad")+"").trim();
					String vidriosATintar =  (request.getParameter("vidriosATintar")+"");
					GlassTintService service = new GlassTintService(vidriosATintar.split("\\|"), oscuridad, tipoTintado);
					item = new ServiceItem(ServiceType.TINTADO_CRISTAL, service, vehicle, fechaServicio, serviceAddress);
				} else if(serviceType == ServiceType.CAMBIO_BATERIA) {
					String marcaBateria =  (request.getParameter("marcaBateria")+"").trim();
					String tipoBateria =  (request.getParameter("tipoBateria")+"").trim();
					String referenciaBateria =  (request.getParameter("referenciaBateria")+"").trim();
					BatteryChangeService service = new BatteryChangeService(marcaBateria, tipoBateria, referenciaBateria);
					item = new ServiceItem(ServiceType.CAMBIO_BATERIA, service, vehicle, fechaServicio, serviceAddress);
				} else if(serviceType == ServiceType.TUNE_UP) { 
					TuneUpService service = new TuneUpService();
					item = new ServiceItem(ServiceType.TUNE_UP, service, vehicle, fechaServicio, serviceAddress);
				} else if (serviceType == ServiceType.CAMBIO_GOMAS) {
					String referenciaGomas =  (request.getParameter("referenciaGomas")+"").trim();
					String marcaPredilecta =  (request.getParameter("marcaPredilecta")+"").trim();
					int quantity =  Integer.parseInt((request.getParameter("quantity")+"").trim());
					TireChangeService service = new TireChangeService(referenciaGomas, marcaPredilecta, quantity);
					item = new ServiceItem(ServiceType.CAMBIO_GOMAS, service, vehicle, fechaServicio, serviceAddress);
				}else {
					selectedService = false;
				}
				if (selectedService) {
					item.setFecha(fecha);
					item.setHorario(horario);
				}
				
				logger.info(METHOD_NAME + " Service: " + item.toString());
				
				if ("add".equalsIgnoreCase(operation) && selectedService) {
					try {
						logger.info(METHOD_NAME + "operation: add --> trabajando con " + item.toString());
						cartUtil.addServiceToCart(item);
					} catch (InvalidItemException e) {
						e.printStackTrace();
						return ERROR;
					}
				} else if ("remove".equalsIgnoreCase(operation) && selectedService) {
					try {
						logger.info(METHOD_NAME + "operation: remove --> trabajando con " + item.toString());
						item.setStartServiceTime(new Date(startServiceTime));
						cartUtil.removeServiceFromCart(item);
					} catch (InvalidItemException e) {
						e.printStackTrace();
						return ERROR;
					}			
				}  else if ("update".equalsIgnoreCase(operation) && selectedService) {
					try {
						logger.info(METHOD_NAME + "operation: update --> trabajando con " + item.toString());
						cartUtil.updateServiceToCart(item);
					} catch (InvalidItemException e) {
						e.printStackTrace();
						return ERROR;
					}
					
				}
				cartUtil.save();
			} else {
				throw new NotExistsException();
			}
		}catch (NumberFormatException ex) {
			ex.printStackTrace();
			return ERROR;
		} catch (NotExistsException e) {
			e.printStackTrace();
			return ERROR;
		}

		logger.info(METHOD_NAME + "  saliendo...");
		return SUCCESS;
	}

	public void getModelo() throws Exception {
		String modelo = (request.getParameter("modeloid")+"").trim();
		String marca = (request.getParameter("marcaid")+"").trim();
		if (marca != null && marca.length() > 0 && modelo !=null && modelo.length() > 0) {
			try {
				int marcaid= Integer.parseInt(marca);
				int modeloid= Integer.parseInt(modelo);
				String str = DatalayerUtil.getModeloDescription(marcaid, modeloid);
				response.setContentType("text/plain");
        		response.setContentLength(str.length());
        		response.getOutputStream().print(str.toString());
        		response.getOutputStream().flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void getModelosFromMarca() throws Exception {
		String marca = (request.getParameter("marca")+"").trim();
		if (marca != null && marca.length() > 0) {
			try {
				int marcaid= Integer.parseInt(marca);
				BusinessHelperLocal helper = new BusinessHelper();
				List<Modelo> modelos = helper.getModelos(helper.getMarcas(marcaid));

                StringBuffer str = new StringBuffer();
                str.append("[");
                Iterator<Modelo> it = modelos.iterator();
                while (it.hasNext()) {
                	Modelo modelo = it.next();
                	str.append("{\"optionValue\": "+modelo.getModeloid()+", \"optionDisplay\": \""+modelo.getModelo()+"\"}");
                	if (it.hasNext())
                		str.append(", ");
                }
                str.append("]");
                response.setContentType("text/plain");
        		response.setContentLength(str.length());
        		response.getOutputStream().print(str.toString());
        		response.getOutputStream().flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	// [ {optionValue: 0, optionDisplay: 'Mark'}, {optionValue:1, optionDisplay: 'Andy'}, {optionValue:2, optionDisplay: 'Richard'}]
	
	/*
	 * 
	 * 		$(function(){
			$("select#ctlJob").change(function(){
				$.getJSON("select.php",{id: $(this).val()}, function(j){
					var options = '';
					for (var i = 0; i < j.length; i++) {
						options += '<option value="' + j[i].optionValue + '">' + j[i].optionDisplay + '</option>';
					}
					$("#ctlPerson").html(options);
					$('#ctlPerson option:first').attr('selected', 'selected');
				})
			})			
		})

	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;		
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
		
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		
	}
}
