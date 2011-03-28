package com.autoservices.web.action.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.autoservice.commons.Constants;
import com.autoservice.commons.ServiceType;
import com.autoservice.entities.Contact;
import com.autoservice.entities.Part;
import com.autoservice.entities.PartCategory;
import com.autoservice.entities.User;
import com.autoservice.entities.Vehicle;
import com.autoservices.web.action.commons.ajax.AjaxCalls;
import com.autoservices.web.session.UserSession;
import com.autoservices.web.util.DatalayerUtil;
import com.autoservices.web.util.Util;
import com.autoservices.web.util.lists.Ciudad;
import com.autoservices.web.util.lists.LabelValueBean;
import com.autoservices.web.util.lists.Marcas;
import com.opensymphony.xwork2.ActionSupport;

public class ServiciosAction extends ActionSupport implements ServletRequestAware {
	static Logger logger = Logger.getLogger(ServiciosAction.class.getName());
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	private boolean logged = false;
	public static int SERVICE_TYPE_OIL_CHANGE=1;
	private int serviceType;
	private Date serviceDate;
	// datos de contacto
	private int pais = 1;		 	 	 	 	 	 	 
	private int ciudad;
	private List<Ciudad> ciudadList;
	private String sector;
	private String calle;
	private String numero;
	private String apto;
	private String edificio;
	private String telefono;
	private String celular;
	private String referencia;

	private int ownCiudad;
	private String ownSector;
	private String ownCalle;
	private String ownNumero;
	private String ownApto;
	private String ownEdificio;
	private String ownTelefono;
	private String ownCelular;
	
	private String notaFinal;

	private List<Part> parts;
	private List<PartCategory> categories;
	private String categoria;
	private String part;
	private User user;
	private List<Marcas> marcas;
	private String marca;
	private String modelo;
	private String anio;
	private String car;
	private String chassis;
	private String descripcion;
	private List<String> aniosList;
	private List<Vehicle> carros;
	private List<LabelValueBean> servicesTimes;
	private String serviceTime;
	private List<LabelValueBean> holidays;

	// aceites
	private List<LabelValueBean> oilTypes;
	private String oilType;

	private List<LabelValueBean> oilBrands;
	private String oilBrand;

	// tintado
	private String cristalesATintar;
	private List<LabelValueBean> crystalTypes;
	
	private String tintadoOscuridad;
	private List<LabelValueBean> tintadoOscuridades;
	
	private String tipoTintado;
	private List<LabelValueBean> tiposTintado;

	// baterias
	private String marcaBateria;
	private List<LabelValueBean> marcasBateria;
	private String tipoBateria;
	private List<LabelValueBean> tiposBateria;
	private String referenciaBateria;
	
	// GOMAS
	private List<LabelValueBean> referenciasGomas;
	private String referenciaGomas;
	private List<LabelValueBean> marcasGoma;
	private String marcaGoma;
	private final String SERVICE_OPTED = "SERVICE_OPTED";
	
	public String cambioAceite() throws Exception {
		request.setAttribute(SERVICE_OPTED, ServiceType.CAMBIO_ACEITE);
		return execute();
	}
	
	public String tintado() throws Exception {
		request.setAttribute(SERVICE_OPTED, ServiceType.TINTADO_CRISTAL);
		return execute();
	}
	public String bateria() throws Exception {
		request.setAttribute(SERVICE_OPTED, ServiceType.CAMBIO_BATERIA);
		return execute();
	}
	public String cerrajeria() throws Exception {
		request.setAttribute(SERVICE_OPTED, ServiceType.CERRAJERIA);
		return execute();
	}
	public String cambioGomas() throws Exception {
		request.setAttribute(SERVICE_OPTED, ServiceType.CAMBIO_GOMAS);
		return execute();
	}
	public String execute() throws Exception {
		String METHOD_NAME="execute()";
		HttpSession session = request.getSession();
		setServicesTimes(Util.getServicesTimes());
		setCiudadList(Util.getCiudades());
		setHolidays(Util.getHolidays((Calendar.getInstance()).get(Calendar.YEAR)));
		logger.info(METHOD_NAME + " : feriados: " + getHolidays());
		if(session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY) == null) {
			setLogged(false);
			setCarros(new ArrayList<Vehicle>());
			setUser(null);
		} else {
			setLogged(true);
			UserSession userSessionObj = (UserSession) session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
			User user = DatalayerUtil.getUser(userSessionObj.getLogin());
			setUser(user);
			Contact contact = user.getContact();
			
			if (contact.getCiudad() != null && contact.getCiudad().length() > 0)
				setOwnCiudad(Integer.parseInt(contact.getCiudad()));

			setOwnSector(contact.getSector());
			setOwnCalle(contact.getCalle());
			setOwnNumero(contact.getNumero());
			setOwnApto(contact.getApto());
			setOwnEdificio("");
			setOwnTelefono(contact.getTelefono());
			setOwnCelular(contact.getCelular());

			if (this.getUser() != null) {
				setCarros(this.getUser().getVehicles());
			}
			
		}
		setOilTypes(Util.getMultipleTypesByType(Util.TYPE_OIL));
		setOilBrands(Util.getMultipleTypesByType(Util.TYPE_OIL_BRANDS));
		setAniosList(Util.getCarYears());
		setMarcas(Util.getMarcas());
		setCrystalTypes(Util.getMultipleTypesByType(Util.TYPE_TINT_CRYSTALS));
		setTintadoOscuridades(Util.getMultipleTypesByType(Util.TYPE_TINT_DARKNESS));
		setTiposTintado(Util.getMultipleTypesByType(Util.TYPE_TINT_QUALITY));
		List<LabelValueBean> marcasBat =  Util.getMultipleTypesByType(Util.TYPE_BATERY_BRAND);
		marcasBat.add(new LabelValueBean("Otra", "otra"));
		setMarcasBateria(marcasBat);
		setTiposBateria(Util.getMultipleTypesByType(Util.TYPE_BATERY_TYPE));
		setMarcasGoma(Util.getMultipleTypesByType(Util.TYPE_TIRE_BRAND));
		setReferenciasGomas(Util.getMultipleTypesByType(Util.TYPE_TIRE_SIZE));
		
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public int getServiceType() {
		return serviceType;
	}
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	public int getPais() {
		return pais;
	}
	public void setPais(int pais) {
		this.pais = pais;
	}
	public int getCiudad() {
		return ciudad;
	}
	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getApto() {
		return apto;
	}
	public void setApto(String apto) {
		this.apto = apto;
	}
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(String notaFinal) {
		this.notaFinal = notaFinal;
	}
	public List<Vehicle> getCarros() {
		return carros;
	}
	public void setCarros(List<Vehicle> carros) {
		this.carros = carros;
	}
	public List<String> getAniosList() {
		return aniosList;
	}
	public void setAniosList(List<String> aniosList) {
		this.aniosList = aniosList;
	}
	public List<Marcas> getMarcas() {
		return marcas;
	}
	public void setMarcas(List<Marcas> marcas) {
		this.marcas = marcas;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public List<Part> getParts() {
		return parts;
	}
	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
	public List<PartCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<PartCategory> categories) {
		this.categories = categories;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<LabelValueBean> getServicesTimes() {
		return servicesTimes;
	}
	public void setServicesTimes(List<LabelValueBean> servicesTimes) {
		this.servicesTimes = servicesTimes;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public List<Ciudad> getCiudadList() {
		return ciudadList;
	}
	public void setCiudadList(List<Ciudad> ciudadList) {
		this.ciudadList = ciudadList;
	}
	public List<LabelValueBean> getHolidays() {
		return holidays;
	}
	public void setHolidays(List<LabelValueBean> holidays) {
		this.holidays = holidays;
	}
	public int getOwnCiudad() {
		return ownCiudad;
	}
	public void setOwnCiudad(int ownCiudad) {
		this.ownCiudad = ownCiudad;
	}
	public String getOwnSector() {
		return ownSector;
	}
	public void setOwnSector(String ownSector) {
		this.ownSector = ownSector;
	}
	public String getOwnCalle() {
		return ownCalle;
	}
	public void setOwnCalle(String ownCalle) {
		this.ownCalle = ownCalle;
	}
	public String getOwnNumero() {
		return ownNumero;
	}
	public void setOwnNumero(String ownNumero) {
		this.ownNumero = ownNumero;
	}
	public String getOwnApto() {
		return ownApto;
	}
	public void setOwnApto(String ownApto) {
		this.ownApto = ownApto;
	}
	public String getOwnEdificio() {
		return ownEdificio;
	}
	public void setOwnEdificio(String ownEdificio) {
		this.ownEdificio = ownEdificio;
	}
	public String getOwnTelefono() {
		return ownTelefono;
	}
	public void setOwnTelefono(String ownTelefono) {
		this.ownTelefono = ownTelefono;
	}
	public String getOwnCelular() {
		return ownCelular;
	}
	public void setOwnCelular(String ownCelular) {
		this.ownCelular = ownCelular;
	}
	public List<LabelValueBean> getOilTypes() {
		return oilTypes;
	}
	public void setOilTypes(List<LabelValueBean> oilTypes) {
		this.oilTypes = oilTypes;
	}
	public String getOilType() {
		return oilType;
	}
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	public List<LabelValueBean> getOilBrands() {
		return oilBrands;
	}
	public void setOilBrands(List<LabelValueBean> oilBrands) {
		this.oilBrands = oilBrands;
	}
	public String getOilBrand() {
		return oilBrand;
	}
	public void setOilBrand(String oilBrand) {
		this.oilBrand = oilBrand;
	}
	public String getCristalesATintar() {
		return cristalesATintar;
	}
	public void setCristalesATintar(String cristalesATintar) {
		this.cristalesATintar = cristalesATintar;
	}
	public List<LabelValueBean> getCrystalTypes() {
		return crystalTypes;
	}
	public void setCrystalTypes(List<LabelValueBean> crystalTypes) {
		this.crystalTypes = crystalTypes;
	}
	public String getTintadoOscuridad() {
		return tintadoOscuridad;
	}
	public void setTintadoOscuridad(String tintadoOscuridad) {
		this.tintadoOscuridad = tintadoOscuridad;
	}
	public List<LabelValueBean> getTintadoOscuridades() {
		return tintadoOscuridades;
	}
	public void setTintadoOscuridades(List<LabelValueBean> tintadoOscuridades) {
		this.tintadoOscuridades = tintadoOscuridades;
	}
	public String getTipoTintado() {
		return tipoTintado;
	}
	public void setTipoTintado(String tipoTintado) {
		this.tipoTintado = tipoTintado;
	}
	public List<LabelValueBean> getTiposTintado() {
		return tiposTintado;
	}
	public void setTiposTintado(List<LabelValueBean> tiposTintado) {
		this.tiposTintado = tiposTintado;
	}
	public String getMarcaBateria() {
		return marcaBateria;
	}
	public void setMarcaBateria(String marcaBateria) {
		this.marcaBateria = marcaBateria;
	}
	public List<LabelValueBean> getMarcasBateria() {
		return marcasBateria;
	}
	public void setMarcasBateria(List<LabelValueBean> marcasBateria) {
		this.marcasBateria = marcasBateria;
	}
	public String getTipoBateria() {
		return tipoBateria;
	}
	public void setTipoBateria(String tipoBateria) {
		this.tipoBateria = tipoBateria;
	}
	public List<LabelValueBean> getTiposBateria() {
		return tiposBateria;
	}
	public void setTiposBateria(List<LabelValueBean> tiposBateria) {
		this.tiposBateria = tiposBateria;
	}
	public String getReferenciaBateria() {
		return referenciaBateria;
	}
	public void setReferenciaBateria(String referenciaBateria) {
		this.referenciaBateria = referenciaBateria;
	}
	public List<LabelValueBean> getReferenciasGomas() {
		return referenciasGomas;
	}
	public void setReferenciasGomas(List<LabelValueBean> referenciasGomas) {
		this.referenciasGomas = referenciasGomas;
	}
	public String getReferenciaGomas() {
		return referenciaGomas;
	}
	public void setReferenciaGomas(String referenciaGomas) {
		this.referenciaGomas = referenciaGomas;
	}
	public List<LabelValueBean> getMarcasGoma() {
		return marcasGoma;
	}
	public void setMarcasGoma(List<LabelValueBean> marcasGoma) {
		this.marcasGoma = marcasGoma;
	}
	public String getMarcaGoma() {
		return marcaGoma;
	}
	public void setMarcaGoma(String marcaGoma) {
		this.marcaGoma = marcaGoma;
	}
}
