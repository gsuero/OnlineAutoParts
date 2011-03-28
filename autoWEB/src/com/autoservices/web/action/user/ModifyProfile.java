package com.autoservices.web.action.user;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import com.autoservice.commons.Constants;
import com.autoservice.ejbs.user.UserBean;
import com.autoservice.ejbs.user.UserBeanLocal;
import com.autoservice.entities.User;
import com.autoservice.entities.Vehicle;
import com.autoservice.exceptions.AutoException;
import com.autoservices.web.session.UserSession;
import com.autoservices.web.util.UserHelper;
import com.autoservices.web.util.Util;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyProfile extends ActionSupport implements
		ServletResponseAware, ServletRequestAware, ServletContextAware {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;

	public String deleteCars() throws Exception {
		String operation = request.getParameter("operation") + "";
		String desc = request.getParameter("desc") + "";
		String chassis = request.getParameter("chas") + "";
		try {
			if ("delete".equalsIgnoreCase(operation)) {
				UserSession userSession = (UserSession) request.getSession()
						.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
				if (userSession != null) {
					String login = userSession.getLogin();
					UserBeanLocal userBean = new UserBean();
					User user = userBean.getUser(login);

					UserHelper.removeVehicle(user, chassis);
				}
			}
		} catch (AutoException ex) {
			addActionError("Imposible borrar : " + desc + " : "
					+ ex.getMessage());
			ex.printStackTrace();
			return ERROR;
		} catch (Exception ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}

	public String getCarsTable() throws Exception {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
		String login = userSession.getLogin();
		UserBeanLocal userBean = new UserBean();
		User user = userBean.getUser(login);
		request.setAttribute("USER_CARS", user.getVehicles());
		return SUCCESS;
	}

	public String saveBasicInformation() throws Exception {
		try {
			UserSession userSession = (UserSession) request.getSession()
					.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
			String login = userSession.getLogin();

			String loginParameter = request.getParameter("login") + "";

			if (login.equalsIgnoreCase(loginParameter)) {
				String operation = request.getParameter("operation") + "";
				String salutation = request.getParameter("salutation") + "";
				String firstname = request.getParameter("firstname") + "";
				String middlename = request.getParameter("middlename") + "";
				String lastname = request.getParameter("lastname") + "";
				String fechaNacimiento = request.getParameter("fechaNacimiento")+ "";
				String password = request.getParameter("password") + "";
				String habitualPit = request.getParameter("habitualPit") + "";
				
				boolean errors = false;
				if (!Util.isSalutationValid(salutation)){
					addActionError("Saludos es incorrecto. Debe elegir una formalidad.");
					errors = true;
				}
				if (middlename != null && middlename.length() > 0 && !Util.isMiddleNameValid(middlename)) {
					addActionError("Inicial de segundo nombre incorrecta.");
					errors = true;
				}
				if (!Util.isFirstNameValid(firstname)){
					addActionError("Primer Nombre es incorrecto.");
					errors = true;
				}
				if (!Util.isLastNameValid(lastname)){
					addActionError("Apellido es incorrecto.");
					errors = true;
				}
				
				if (!Util.isBirthDateValid(fechaNacimiento)){
					addActionError("Fecha de nacimiento es incorrecta.");
					errors = true;
				}
				if (password != null && password.length() > 0 && !Util.isPasswordValid(password)) {
					addActionError("Contrase\00f1a es incorrecta.");
					errors = true;
				}
/*				if (!Util.isValidEmailAddress(getEMail())){
					addActionError("eMail", "Direcci\00f3n de correo es incorrecta.");
					errors = true;
				}
				*/
				if (habitualPit != null && habitualPit.length() > 0 && !Util.isHabitualPitValid(habitualPit)) {
					addActionError("Taller habitual es inv\00e1lido.");
					errors = true;
				}
				if (errors) 
					return ERROR;

				UserBeanLocal userBean = new UserBean();
				boolean updated = userBean.updateUser(login,
									salutation,
									firstname,
									(middlename != null && middlename.length() > 0 ? middlename.toCharArray()[0] : ' '),
									lastname,
									Constants.COMMON_DATE_FORMAT.parse(fechaNacimiento),
									password,
									"",
									habitualPit);
				
				if (updated) {
					return SUCCESS;
				} else {
					addActionError("Datos de usuarios no pudieron ser guardados...");
					return ERROR;
				}
				// agregando vehiculo...
			} else {
				addActionError("Usuario no corresponde. ");
				return ERROR;
			}

		} catch (IllegalArgumentException ex) {
			addActionError("Fecha de nacimiento es invalida.");
			return ERROR;
		} catch (NullPointerException ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		} catch (Exception ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		}
	}

	public String saveContactInformation() throws Exception {

		// "operation=update&ciudad="+ciudad+"&sector="+sector+"&calle="+calle+"&numero="+numero+"&apto="+apto+"&telefono="+telefono+"&celular="+celular;
		try {
			UserSession userSession = (UserSession) request.getSession()
					.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
			String login = userSession.getLogin();

			String operation = request.getParameter("operation") + "";
			String ciudad = request.getParameter("ciudad") + "";
			String sector = request.getParameter("sector") + "";
			String calle = request.getParameter("calle") + "";
			String numero = request.getParameter("numero") + "";
			String apto = request.getParameter("apto")+ "";
			String telefono = request.getParameter("telefono") + "";
			String celular = request.getParameter("celular") + "";
				
				boolean errors = false;
				if (!Util.isSectorValid(sector)){
					addActionError("El campo Sector es incorrecto.");
					errors = true;
				}
				if(!Util.isCalleValid(calle)) {
					addActionError("El campo Calle es incorrecto.");
					errors = true;
				}
				if (!Util.isNumeroContacto(numero)) {
					addActionError("El campo N\00famero es incorrecto.");
					errors = true;
				}
				if (!Util.isAptoValid(apto)) {
					addActionError("El campo Apto es incorrecto.");
					errors = true;			
				}
				
				if (!Util.isTelefonoValid(telefono)) {
					addActionError("El campo Tel\00e9fono es incorrecto.");
					errors = true;
				}
				if (!Util.isTelefonoValid(celular)) {
					addActionError("El campo Celular es incorrecto.");
					errors = true;
				} 				
				
				if (errors) 
					return ERROR;

				UserBeanLocal userBean = new UserBean();
				int ciudadInt = Integer.parseInt(ciudad);
				boolean updated = userBean.updateContact(login, Constants.DEFAULT_COUNTRY, ciudadInt, sector, calle, numero, apto, telefono, celular);
				
				if (updated) {
					return SUCCESS;
				} else {
					addActionError("Datos de usuarios no pudieron ser guardados...");
					return ERROR;
				}

		} catch (NumberFormatException ex) {
			addActionError("Ciudad es invalida.");
			return ERROR;
		} catch (NullPointerException ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		} catch (Exception ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		}
	}

	public String saveCars() throws Exception {
		try {
			UserSession userSession = (UserSession) request.getSession()
					.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
			String login = userSession.getLogin();
			UserBeanLocal userBean = new UserBean();
			// data:
			// "operation=[add|delete|update]&desc=0&chas=0&ano=0&model=0&brand=0",
			String operation = request.getParameter("operation") + "";
			String desc = request.getParameter("desc") + "";
			String chassis = request.getParameter("chas") + "";
			String marca = request.getParameter("brand") + "";
			String modelo = request.getParameter("model") + "";
			String anio = request.getParameter("ano") + "";

			// agregando vehiculo...
			Vehicle vehicle = new Vehicle();
			try {
				vehicle.setAnio(Integer.parseInt(anio));
			} catch (NumberFormatException ex) {
				addActionError(anio + " año inválido.");
				return ERROR;
			}
			vehicle.setChassis(chassis);
			vehicle.setDescription(desc);
			vehicle.setMarca(marca);
			vehicle.setModelo(modelo);
			if ("add".equalsIgnoreCase(operation)) {

				userBean.addVehicle(login, vehicle);
			} else if ("update".equalsIgnoreCase(operation)) {
				String carid = (request.getParameter("carid") + "").trim();
				try {
					vehicle.setId(Integer.parseInt(carid));
				} catch (NumberFormatException ex) {
					addActionError("Vehículo inválido");
					return ERROR;
				}
				userBean.updateVehicle(login, vehicle);
			}

		} catch (NullPointerException ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		} catch (Exception ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}

	public String execute() throws Exception {

		return SUCCESS;
	}

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
