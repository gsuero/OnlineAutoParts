package com.autoservices.web.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.autoservice.commons.Constants;
import com.autoservice.commons.security.PasswordBusiness;
import com.autoservice.ejbs.user.UserBean;
import com.autoservice.ejbs.user.UserBeanLocal;
import com.autoservice.entities.Contact;
import com.autoservice.entities.User;
import com.autoservice.entities.Vehicle;
import com.autoservice.exceptions.AutoException;
import com.autoservice.exceptions.ConfirmationMailException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservice.exceptions.NotExistsUserException;
import com.autoservice.exceptions.NotValidRegistrationException;
import com.autoservices.web.util.UserConfirmationHelper;
import com.autoservices.web.util.Util;
import com.autoservices.web.util.lists.Ciudad;
import com.opensymphony.xwork2.ActionSupport;

public class SignUp extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	/*
	 * TODO que los checkbox de los carros si hay error se queden marcados..
	 */
	private String login;
	private ArrayList<String> salutationList; 
	private String salutation;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthDate;
	private String password;
	private String passwordConfirmation;
	private String eMail;
	private String referal;
	private String habitualPit;
	// contacts
	private Contact contact;
	private String pais;
	private String ciudad;
	private ArrayList<Ciudad> ciudadList;
	private String sector;
	private String calle;
	private String numero;
	private String apto;
	private String telefono;
	private String celular;
	
	// vehicles
	private List<Vehicle> vehicles;
	private List<Boolean> car;
	private List<String> descripcion;
	private List<String> chassis;
	private List<String> marca;
	private List<String> modelo;
	private List<String> anio;
	
	public String populateAction() {
		populate();
		return SUCCESS;
	}
	public void populate(){
		this.salutationList = new ArrayList<String>();
		salutationList.add("Sr.");
		salutationList.add("Sra.");
		salutationList.add("Srta.");
		try {
			ciudadList = Util.getCiudades();
		} catch (NotExistsException ex) {
			ciudadList = new ArrayList<Ciudad>();
		}
	}
	public String execute() throws Exception {
		
		// validando datos generales...\
		UserBeanLocal userLogic = null;
		User user = new User();
		try {
			userLogic = new UserBean();
			try { 
				boolean errors = false;
				if (login != null && login.length() > 0) {
					boolean existUser = false;
					try {
						userLogic.getUser(getLogin());
						existUser = true;
					} catch (NotExistsUserException ex) {
						existUser = false;
					} catch (Exception ex) {
						throw ex;
					}
					
					if (existUser) {
						addFieldError("login","Nombre de usuario ya esta siendo utilizado.");
						errors = true;
					} else {
						if (!Util.isSalutationValid(getSalutation())){
							addFieldError("salutation","Saludos es incorrecto. Debe elegir una formalidad.");
							errors = true;
						}
						if (getMiddleName() != null && getMiddleName().length() > 0 && !Util.isMiddleNameValid(getMiddleName())) {
							addFieldError("middleName","Inicial de segundo nombre incorrecta.");
							errors = true;
						}
						if (!Util.isFirstNameValid(getFirstName())){
							addFieldError("firstName","Primer Nombre es incorrecto.");
							errors = true;
						}
						if (!Util.isLastNameValid(getLastName())){
							addFieldError("lastName", "Apellido es incorrecto.");
							errors = true;
						}
						
						if (!Util.isBirthDateValid(getBirthDate())){
							addFieldError("password", "Fecha de nacimiento es incorrecta.");
							errors = true;
						}
						if (!Util.isPasswordValid(getPassword())) {
							addFieldError("password", "Contrase\00f1a es incorrecta.");
							errors = true;
						}else if (!Util.isPasswordValid(getPassword(), getPasswordConfirmation())) {
							addFieldError("passwordConfirmation", "contrase\00f1a y la confirmaci\00f3n deben coincidir.");
							errors = true;
						}
						if (!Util.isValidEmailAddress(getEMail())){
							addFieldError("eMail", "Direcci\00f3n de correo es incorrecta.");
							errors = true;
						}
						if (getReferal() != null && getReferal().length() > 0 && !Util.isReferalValid(getReferal())) {
							addFieldError("referal","Campo 'Com se entero de nosotros' es incorrecto.");
							errors = true;
						}
						if (getHabitualPit() != null && getHabitualPit().length() > 0 && !Util.isHabitualPitValid(getHabitualPit())) {
							addFieldError("habitualPit","Taller habitual es inv\00e1lido.");
							errors = true;
						}
					}
					
				}
				if (errors){
					addActionError("Existen errores en el formulario.");
					throw new NotValidRegistrationException();
				}
			} catch (NotValidRegistrationException ex){
				throw ex;
			} catch (Exception ex) {
				throw new Exception("Agregando datos personales [" + ex.getMessage() + "]");
			}		
			
			// validando datos de contacto...
			try { 
				boolean errors = false;
				Contact contact = new Contact();

				if (Util.isSectorValid(getSector())){
					contact.setSector(getSector());
				} else {
					addActionError("El campo Sector es incorrecto.");
					errors = true;
				}
				if(Util.isCalleValid(getCalle())) {
					contact.setCalle(getCalle());
				}  else {
					addActionError("El campo Calle es incorrecto.");
					errors = true;
				}
				if (Util.isNumeroContacto(getNumero())) {
					contact.setNumero(getNumero());
				} else {
					addActionError("El campo N\00famero es incorrecto.");
					errors = true;
				}
				if (Util.isAptoValid(getApto())) {
					contact.setApto(getApto());
				}
				
				if (Util.isTelefonoValid(getTelefono())) {
					contact.setTelefono(getTelefono());
					
				} else {
					addActionError("El campo Tel\00e9fono es incorrecto.");
					errors = true;
				}
				if (Util.isTelefonoValid(getCelular())) {
					contact.setCelular(getCelular());
				} 
				
				if (!errors) {
					contact.setPais("1");
					contact.setCiudad("1");
					contact.setStatus(Constants.GLOBAL_STATUS_ACTIVATED);
					user.setContact(contact);
				} else {
					throw new NotValidRegistrationException();
				}
					
			} catch (NotValidRegistrationException ex) {  
				throw ex;
			} catch (Exception ex) {
				throw new Exception("Agregando Contactos [" + ex.getMessage() + "]");
			}
			
			// agregando automoviles...
			try {
				boolean errors = false;
				//vehicles = new List();
				if (getCar() != null && getDescripcion() != null && getChassis() != null 
						&& getMarca() != null && getModelo() != null && getAnio() !=null) {
					Iterator<Boolean> it = getCar().iterator();
					int index = 0;
					while (it.hasNext()) {
						boolean car = it.next();
						if (car) {
							Vehicle veh = new Vehicle();
							String descIter = getDescripcion().get(index)+"";
							String carError = (descIter != null && descIter.length() > 0 ? descIter : "Carro #" + index);
							String anioIter =getAnio().get(index);
							String chassisIter = getChassis().get(index);
							String marcaIter = getMarca().get(index);
							String modeloIter = getModelo().get(index);
							
							if (Util.isChassisValid(chassisIter)) {
								veh.setChassis(chassisIter);
							} else {
								addActionError(carError + ": Formato de Chassis incorrecto.");
								errors = true;
								break;
							}
							if (Util.isCarNameValid(descIter)) {
								veh.setDescription(descIter);
							} else {
								addActionError(carError + ": Descripci\00f3n inv\00e1lida.");
								errors = true;
								break;
							}
							if (Util.isMarcaValid(marcaIter)) {
								veh.setMarca(marcaIter);
							} else {
								addActionError(carError + ": Marca inv\00e1lida.");
								errors = true;
								break;
							}
							if (Util.isModeloValid(marcaIter, modeloIter)) {
								veh.setModelo(modeloIter);
							} else {
								addActionError(carError + ": Marca inv\00e1lida.");
								errors = true;
								break;
							}						
							if (Util.isAnioValid(anioIter)) {
								veh.setAnio(Integer.parseInt(anioIter));
							} else {
								addActionError(carError + ": Formato de A\u00F1o incorrecto.");
								errors = true;
								break;
							}
							user.addVehicle(veh);
						}
						index++;
						}
					if (errors) {
						throw new NotValidRegistrationException();
					}
				}
				
			}catch (NotValidRegistrationException ex) {
				throw ex;
				
			} catch (Exception ex) {
				throw new Exception("Agregando veh\00edculos [" + ex.getMessage() + "]");
			}
			
		} catch (NotValidRegistrationException ex) {
			populate();
			return ERROR;
		} catch (Exception ex) {
			addActionError("Error de aplicaci\00f3n : "+ ex.getMessage());
			populate();
			return ERROR;
		}
		
		try {
			if (userLogic != null) {
				user.setBirthdate(Constants.BIRTH_DATE_FORMAT.format(getBirthDate()));
				user.setEmail(getEMail());
				user.setFirstName(getFirstName());
				user.setMiddleName((getMiddleName() != null && getMiddleName().length() > 0 ? getMiddleName().toCharArray()[0] : ' '));
				user.setLastName(getLastName());
				user.setHabitualPit(getHabitualPit());
				user.setLogin(getLogin());
				user.setPassword(new PasswordBusiness(getPassword()).getMD5());
				user.setReferal(getReferal());
				user.setSalutation(getSalutation());
				user.setUserType(userLogic.getUserType(0));
				user.setStatus(Constants.USER_STATUS_UNCONFIRM);
				try {
					userLogic.createUser(user);
					try {
						UserConfirmationHelper confirmationHelper = new UserConfirmationHelper(user);
						confirmationHelper.generateConfirmationCode();
						if (confirmationHelper.sendConfirmationMail()) {
							
						}
					} catch (ConfirmationMailException ex) {
						// no se pudo crear, esto deberia SIEMPRE FUNCIONAR 
						// sino, existe la opcion de reenviar mail de confirmacion...
						throw ex;
					} catch (AutoException ex) {
						// no se pudo crear, esto deberia SIEMPRE FUNCIONAR 
						// sino, existe la opcion de reenviar mail de confirmacion...						
						throw ex;
					}
				} catch (Exception ex) {
					throw new Exception("error llamando createUser" + ex.getMessage());
				}
				
			}
		} catch(Exception ex) {
			populate();
			addActionError("ERROR intentando crear el usuario " + ex.getMessage());
			return ERROR;
		}
		getRequest().setAttribute("USER", getLogin()+"");
		getRequest().setAttribute("NAMES", getFirstName() + " " + getLastName());
		getRequest().setAttribute("EMAIL", getEMail()+"");
		//getRequest().
		addActionMessage("Registro exitoso!.");
		return SUCCESS;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public ArrayList<String> getSalutationList() {
		return salutationList;
	}
	public void setSalutationList(ArrayList<String> salutationList) {
		this.salutationList = salutationList;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	public String getReferal() {
		return referal;
	}
	public void setReferal(String referal) {
		this.referal = referal;
	}
	public String getHabitualPit() {
		return habitualPit;
	}
	public void setHabitualPit(String habitualPit) {
		this.habitualPit = habitualPit;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public ArrayList<Ciudad> getCiudadList() {
		return ciudadList;
	}
	public void setCiudadList(ArrayList<Ciudad> ciudadList) {
		this.ciudadList = ciudadList;
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
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public List<Boolean> getCar() {
		return car;
	}
	public void setCar(List<Boolean> car) {
		this.car = car;
	}
	public List<String> getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(List<String> descripcion) {
		this.descripcion = descripcion;
	}
	public List<String> getChassis() {
		return chassis;
	}
	public void setChassis(List<String> chassis) {
		this.chassis = chassis;
	}
	public List<String> getMarca() {
		return marca;
	}
	public void setMarca(List<String> marca) {
		this.marca = marca;
	}
	public List<String> getModelo() {
		return modelo;
	}
	public void setModelo(List<String> modelo) {
		this.modelo = modelo;
	}
	public List<String> getAnio() {
		return anio;
	}
	public void setAnio(List<String> anio) {
		this.anio = anio;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request =  request;
		
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
}
