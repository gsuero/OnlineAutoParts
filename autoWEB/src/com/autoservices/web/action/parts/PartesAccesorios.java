package com.autoservices.web.action.parts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.autoservice.commons.Constants;
import com.autoservice.ejbs.business.BusinessHelper;
import com.autoservice.ejbs.business.BusinessHelperLocal;
import com.autoservice.entities.Part;
import com.autoservice.entities.PartCategory;
import com.autoservice.entities.User;
import com.autoservice.entities.Vehicle;
import com.autoservice.entities.vehicles.Modelo;
import com.autoservices.web.session.UserSession;
import com.autoservices.web.util.DatalayerUtil;
import com.autoservices.web.util.Util;
import com.autoservices.web.util.lists.Marcas;
import com.autoservices.web.util.objects.CartVehicle;
import com.opensymphony.xwork2.ActionSupport;

public class PartesAccesorios extends ActionSupport implements
		ServletResponseAware, ServletRequestAware, ServletContextAware {
//	private static Log logger = LogFactory.getLog(PartCategories.class);
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;

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
	
	private boolean logged = false;
	
	public String execute() throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY) == null) {
			setLogged(false);
			setCarros(new ArrayList<Vehicle>());
			setUser(null);
		} else {
			setLogged(true);
			UserSession userSessionObj = (UserSession) session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
			setUser(DatalayerUtil.getUser(userSessionObj.getLogin()));
			if (this.getUser() != null) {
				setCarros(this.getUser().getVehicles());
			/*	try {
					List<CartVehicle> vehsList = new ArrayList<CartVehicle>();
					Iterator<Vehicle> iter = this.getUser().getVehicles().iterator();
					while (iter.hasNext()) {
						Vehicle other = iter.next();
						CartVehicle vehs = new CartVehicle();
						vehs.setId(other.getId());
						vehs.setDescription(other.getDescription());
						vehs.setChassis(other.getChassis());
						vehs.setMarca(other.getMarca());
						vehs.setModelo(other.getModelo());
						//vehs.setMarca(DatalayerUtil.getMarcaDescription(other.getMarca()));
						//vehs.setModelo(DatalayerUtil.getModeloDescription(other.getModelo()));
						vehs.setAnio(other.getAnio());
						vehsList.add(vehs);
					}
					setCarros(vehsList);
				} catch (Exception ex) {
					ex.printStackTrace();
				}*/
			}
		}
		setAniosList(Util.getCarYears());
		setMarcas(Util.getMarcas());
		
		BusinessHelper helper = new BusinessHelper();
		setCategories(helper.getPiezasCategories());
		if (getCategories() != null && getCategories().get(0) != null) {
			setCategoria(getCategories().get(0).getCategoryId()+"");
			//setParts(helper.getParts(getCategories().get(0).getCategoryId()));
			setParts(helper.getAllParts());
		} else {
			setCategories(new ArrayList<PartCategory>());
			setParts(new ArrayList<Part>());
		}
		

		return SUCCESS;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;		
	/*	int a=0;
		int count = parts.size();
		for (;a< count;a++) {
			Part part = parts.get(a);
			if (part.getCategory() == null)
				this.parts.remove(a);
		}*/
	}

	public List<PartCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<PartCategory> categories) {	
		this.categories = categories;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
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

	public List<Marcas> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marcas> marcas) {
		this.marcas = marcas;
	}

	public List<String> getAniosList() {
		return aniosList;
	}

	public void setAniosList(List<String> aniosList) {
		this.aniosList = aniosList;
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

	public List<Vehicle> getCarros() {
		return carros;
	}

	public void setCarros(List<Vehicle> list) {
		this.carros = list;
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
}
