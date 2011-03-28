package com.autoservice.ejbs.user;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import com.autoservice.commons.Constants;
import com.autoservice.commons.security.PasswordBusiness;
import com.autoservice.entities.Contact;
import com.autoservice.entities.MailTemplate;
import com.autoservice.entities.User;
import com.autoservice.entities.UserSession;
import com.autoservice.entities.UserType;
import com.autoservice.entities.Vehicle;
import com.autoservice.util.EjbConstants;
import com.autoservice.util.JPAUtil;
import com.autoservice.exceptions.AutoException;
import com.autoservice.exceptions.BadEmailException;
import com.autoservice.exceptions.InvalidUserAndPasswordException;
import com.autoservice.exceptions.NotExistsDepartmentException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservice.exceptions.NotExistsUserException;

/**
 * Session Bean implementation class UserBean
 * @author Garis M. Suero
 * 06/05/2010
 * AutoPiezas01
 */
@Stateless(name="UserBean", mappedName="ejb/UserBeanJNDI")
public class UserBean implements UserBeanLocal {
	@PersistenceUnit(name="myAutoService")
	public EntityManager em;

    public UserBean() {
    	em = new JPAUtil().getEMF().createEntityManager();
    }
    
    public String sayHello(String name) { 	
        return "Hello " + name + "!"; 	
    } 

    @SuppressWarnings("unchecked")
    public User getUser(String login) throws NotExistsUserException
    {
    	User user = null;
		try {
			em.getTransaction().begin();
			Query query = em.createNamedQuery("User.findByLogin");
			query.setParameter("login", login);
			List<User> userList = query.getResultList();
			if (userList.size() > 0) {
	    		user = (User)userList.get(0);
			} else {
				throw new Exception();
			}
			em.flush();
    		em.getTransaction().commit();
		} catch (Exception ex) {
			throw new NotExistsUserException(EjbConstants.MESSAGE_NO_USER_EXISTS);
		}
    	return user;
    }
    
    @SuppressWarnings("unchecked")
    public User getUser(int userid) throws NotExistsUserException
    {
    	User user = null;
		try {
			Query query = em.createNamedQuery("User.findByUserId");
			query.setParameter("userId", userid);
			List<User> userList = query.getResultList();
			if (userList.size() > 0) {
	    		user = (User)userList.get(0);
			} else {
				user = null; 
			}
		} catch (Exception ex) {
			throw new NotExistsUserException(EjbConstants.MESSAGE_NO_USER_EXISTS  + "PRUEBA : "  + ex.getMessage());
		}
    	return user;
    }
    
    @SuppressWarnings("unchecked")
    public boolean changePassword(String login, String currentPassword, String newPassword) throws Exception
    {
    	boolean result = true;
    	try {
    		em.getTransaction().begin();
    		String currentPasswordHash = new PasswordBusiness(currentPassword).getMD5();
    		Query query = em.createNamedQuery("User.findByLoginNPassword");
    		query.setParameter("login", login);
    		query.setParameter("password",currentPasswordHash);
    		try {
    			List<User> userList = query.getResultList();
    			if (userList.size() > 0) {
    	    		User user = userList.get(0);
    				user.setPassword(new PasswordBusiness(newPassword).getMD5());
    				em.persist(user);
    			} else {
    				result = false; 
    			}
    		} catch (IndexOutOfBoundsException ex) {
    			result = false;
    		}
    		em.getTransaction().commit();
    	} catch (Exception er) {
    		throw er;
    	}
    	return result;
    }
    
    public int createUser(int userType, String login, String salutation, String firstName, 
			char middleName, String lastName, Date birthDate, String password, String email, String referal, String habitualPit) throws NotExistsDepartmentException {
        User e = new User();
    	
        try {
	        e.setUserType(getUserType(userType));
	        e.setLogin(login);
	        e.setSalutation(salutation);
	        e.setFirstName(firstName);
	        e.setMiddleName(middleName);
	        e.setLastName(lastName);
	        e.setBirthdate(Constants.BIRTH_DATE_FORMAT.format(birthDate));
	        e.setPassword(password);
	        e.setEmail(email);
	        e.setLastLogin(Constants.COMMON_FULL_DATE_FORMAT.format(new Date()));
	        e.setReferal(referal);
	        e.setHabitualPit(habitualPit);
        } catch (NotExistsDepartmentException ex) { 
    		throw ex;
    	}
        return createUser(e);
    }
    public int createUser(User user) {
    	System.out.println("CREANDO USUARIO : " + user.toString());
    	int userId = -1;
    	try {
	        em.getTransaction().begin();
	        em.persist(user);
	        em.flush();
	        em.getTransaction().commit();
	        userId = getUser(user.getLogin()).getUserId();
    	}  catch (NullPointerException ex) {
    		userId = -1;    		
    	} catch (Exception ex) {
    		System.out.println("[EXCEPCION]!\n" + ex.getMessage());
    		ex.printStackTrace();
    	}
        return userId;
    }
    
    public boolean updateUser(String login, String salutation, String firstName, char middleName, String lastName, Date birthdate, String password, String email, String habitualPit) throws NotExistsUserException, BadEmailException {
    	boolean result = true;
    	try {
   	    	User user = getUser(login);
    		em.getTransaction().begin();
   	    	if (password != null && password.length() > 0){
   	   	    	String newPassword = new PasswordBusiness(password).getMD5();
   	   	    	if (!newPassword.equals(user.getPassword())) {
   	   	    		user.setPassword(newPassword);
   	   	    	}
   	    	}
   	    	if (salutation!= null && salutation.length() > 0 && !salutation.equals(user.getSalutation())){
   	    		user.setSalutation(salutation);
   	    	}
   	    	
   	    	if (firstName!= null && firstName.length() > 0 && !firstName.equals(user.getFirstName()))  {
   	    		user.setFirstName(firstName);
   	    	}
   	    	
   	    	if (middleName != '-' && middleName != user.getMiddleName()) {
   	    		user.setMiddleName(middleName);
   	    	}
   	    	
   	    	if (lastName!= null && lastName.length() > 0 && !lastName.equals(user.getLastName())) {
   	    		user.setLastName(lastName);
   	    	}
   	    	
   	    	if (email!= null && email.length() > 0 && !email.equals(user.getEmail())) {
   	    		user.setEmail(email);
   	    	}
   	    	if (habitualPit!= null && habitualPit.length() > 0 && !habitualPit.equals(user.getHabitualPit())) {
   	    		user.setHabitualPit(habitualPit);
   	    	}
   	    	if (birthdate!= null) {
   	    		user.setBirthdate(Constants.BIRTH_DATE_FORMAT.format(birthdate));
   	    	}
   	    	em.flush();
    		em.getTransaction().commit();
    	} catch (NotExistsUserException er) {
    		throw er;
    	}catch (BadEmailException er) {
    		throw er;
    	} catch (Exception e) {
    		System.out.println("EXCEPCION!\n" + e.getMessage());
    		e.printStackTrace();
    		result = false;
		}
    	return result;
    }
    public void updateUser(User user) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        //em.persist(user);
	        
	        em.merge(user);
	        em.flush();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    
    @SuppressWarnings("unchecked")
	public boolean isValidUserAndPassword(String login, String currentPassword) throws InvalidUserAndPasswordException
    {
    	boolean result = false;
    	try {
    		em.getTransaction().begin();
    		String currentPasswordHash = new PasswordBusiness(currentPassword).getMD5();
    		Query query = em.createNamedQuery("User.findByLoginNPassword");
    		query.setParameter("login", login);
    		query.setParameter("password",currentPasswordHash);
   			List<User> userList = query.getResultList();
   			if (userList.size() > 0) {
   				result = true;
   			} else {
   				throw new InvalidUserAndPasswordException(EjbConstants.MESSAGE_NOT_VALID_USER_AND_PASSWORD); 
   			}
    		em.getTransaction().commit();
    	} catch (InvalidUserAndPasswordException er) {
    		throw er;
    	} catch (NoSuchAlgorithmException er) {
    		throw new InvalidUserAndPasswordException(EjbConstants.MESSAGE_APPLICATION_ERROR  + " Mensaje: " + er.getMessage());
    	}catch (Exception er) {
    		er.printStackTrace();
    		throw new InvalidUserAndPasswordException(EjbConstants.MESSAGE_UNKNOWN_ERROR + " : " + er.getMessage());
    	}
    	return result;
    }
    @SuppressWarnings("unchecked")
    public List<UserType> getAllUserTypes() {
    	List<UserType> userTypes = new ArrayList<UserType>();
    	Query query = em.createNamedQuery("userType.findAll");
    	userTypes = query.getResultList();
    	return userTypes;
    }
    @SuppressWarnings("unchecked")
    public List<UserType> getUserTypes(boolean admin, int status) {
    	List<UserType> userTypes = new ArrayList<UserType>();
    	Query query = em.createNamedQuery("userType.findByCriteria");
		query.setParameter("admin", (admin ? "S" : "N"));
		query.setParameter("status", (status == 1 ? "1" : "0"));
		userTypes = query.getResultList();
    	return userTypes;
    }
    public UserType getUserType(int type) throws NotExistsDepartmentException {
    	UserType userType = null;
    	try{
	    	userType = (UserType) em.find(UserType.class,type);
    	}catch (Exception ex){
    		userType = null;
    	}
    	if (userType == null) 
    		throw new NotExistsDepartmentException(EjbConstants.MESSAGE_NO_DEPARMENT_EXISTS);
    	
    	return userType;
    }
       
    public boolean updateContact(String login, int pais, int ciudad, String sector, String calle, String numero, String apto, String telefono, String celular) {
    	try {
    		User user = null;
			em.getTransaction().begin();
			Query query = em.createNamedQuery("User.findByLogin");
			query.setParameter("login", login);
			List<User> userList = query.getResultList();
			if (userList.size() > 0) {
	    		user = (User)userList.get(0);
	    		Contact contact = user.getContact();
	    		if (!(pais+"").equals(contact.getPais())){
	 	        	contact.setPais(pais+"");
	 	        }
	 	        if (!(ciudad+"").equals(contact.getCiudad())){
	 	        	contact.setCiudad(ciudad+"");
	 	        }
	 	        if (sector != null && !sector.equals(contact.getSector())){
	 	        	contact.setSector(sector);
	 	        }
	 	        if (calle != null && !calle.equals(contact.getCalle())){
	 	        	contact.setCalle(calle);
	 	        }
	 	        if (numero != null && !numero.equals(contact.getNumero())){
	 	        	contact.setNumero(numero);
	 	        }
	 	        if (apto != null && !apto.equals(contact.getApto())){
	 	        	contact.setApto(apto);
	 	        }
	 	        if (telefono != null && !telefono.equals(contact.getTelefono())){
	 	        	contact.setTelefono(telefono);
	 	        }
	 	        if (celular != null && !celular.equals(contact.getCelular())){
	 	        	contact.setCelular(celular);
	 	        }
			} else {
				throw new Exception();
			}
			em.flush();
    		em.getTransaction().commit();
	        
    	} catch (Exception ex) {
    		System.out.println("[EXCEPCION]!\n" + ex.getMessage());
    		ex.printStackTrace();
    		return false;
    	}
        return true;
    }
    
    private Contact getContact(int userId) throws NotExistsUserException
    {
    	Contact contact = null;
		try {
			contact = getUser(userId).getContact();
		} catch (Exception ex) {
			throw new NotExistsUserException(EjbConstants.MESSAGE_NO_USER_EXISTS);
		}
    	return contact;
    }
//  public boolean createContact(User user, String pais, String ciudad, String sector, String calle, String numero, String apto, String telefono, String celular) {
//	try {
//        em.getTransaction().begin();
//        Contact e = new Contact();
//        
//        e.setUserId(user.getUserId());
//        e.setPais(pais);
//        e.setCiudad(ciudad);
//        e.setSector(sector);
//        e.setCalle(calle);
//        e.setNumero(numero);
//        e.setApto(apto);
//        e.setTelefono(telefono);
//        e.setCelular(celular);
//        
//        user.setContact(e);
//        
//        em.persist(user);
//        em.flush();
//        em.getTransaction().commit();
//	} catch (Exception ex) {
//		System.out.println("[EXCEPCION]!\n" + ex.getMessage());
//		ex.printStackTrace();
//		return false;
//	}
//    return true;
//}
    
    
    public void createUserSession(UserSession userSession) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        em.persist(userSession);
	        em.flush();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    public UserSession getUserSession(String serie, String token) throws NotExistsException
    {
    	UserSession userSession = null;
		try {
			Query query = em.createNamedQuery("UserSession.findByAll");
		//	query.setParameter("username", username);
			query.setParameter("serie", serie);
			query.setParameter("token", token);
			List<UserSession> confirmationList = query.getResultList();
			if (confirmationList.size() > 0) {
	    		userSession = confirmationList.get(0);
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(MailTemplate.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS + " " + ex.getMessage());
		}
    	return userSession;
    }
    
    public void updateUserSession(UserSession userSession) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        //em.persist(userSession);
	        //em.flush();
	        em.merge(userSession);
	        em.flush();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    
    public UserSession getUserSessionByUserId(String username) throws NotExistsException
    {
    	UserSession userSession = null;
		try {
			Query query = em.createNamedQuery("UserSession.findBySerie");
			query.setParameter("serie", new PasswordBusiness(username).getMD5());
			List<UserSession> confirmationList = query.getResultList();
			if (confirmationList.size() > 0) {
	    		userSession = confirmationList.get(0);
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(MailTemplate.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS + " " + ex.getMessage());
		}
    	return userSession;
    }
    
    public void deleteUserSession(UserSession userSession) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        em.remove(userSession);
	        em.flush();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    public void addVehicle(String login, Vehicle vehicle) throws AutoException {
    	try {
    		User user = null;
			em.getTransaction().begin();
			Query query = em.createNamedQuery("User.findByLogin");
			query.setParameter("login", login);
			List<User> userList = query.getResultList();
			if (userList.size() > 0) {
	    		user = (User)userList.get(0);
	    		user.addVehicle(vehicle);
			} else {
				throw new Exception();
			}
			em.flush();
    		em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    public Vehicle getVehicle(int vehicleid) throws NotExistsException, AutoException {
    	try {
			em.getTransaction().begin();
			Query query = em.createNamedQuery("vehicle.findById");
			query.setParameter("vehicleid", vehicleid);
			Vehicle veh = (Vehicle) query.getSingleResult();
			if (veh == null || veh.getId() <= 0)
				throw new NotExistsException("Automovil no existe");

			em.flush();
    		em.getTransaction().commit();
			return veh;
    	}  catch (NotExistsException ex) {
    		throw ex;   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    
    public void updateVehicle(String login, Vehicle vehicle) throws AutoException {
    	try {
    		User user = null;
			em.getTransaction().begin();
			Query query = em.createNamedQuery("User.findByLogin");
			query.setParameter("login", login);
			List<User> userList = query.getResultList();
			if (userList.size() > 0) {
	    		user = (User)userList.get(0);
	    		user.updateVehicle(vehicle);
			} else {
				throw new Exception();
			}
			em.flush();
    		em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    public void removeVehicle(Vehicle vehicle) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        em.remove(vehicle);
	        em.flush();
	        em.getTransaction().commit();
	        
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    public void removeVehicle(String chassis) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        Query query = em.createNamedQuery("vehicle.findByChassis");
			query.setParameter("chassis", chassis);
			List<Vehicle> vehicleList = query.getResultList();
			Vehicle vehicle= null;
			if (vehicleList.size() > 0) {
	    		vehicle = vehicleList.get(0);
	    		User user = vehicle.getUser();
	    		vehicle.setUser(null);
	    		em.remove(vehicle);
	    		user.removeVehicle(chassis);
	    		//em.persist(user);
	    		em.flush();
			} else {
				throw new Exception(" no existen ");
			}
	        em.getTransaction().commit();
	        
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}    	
    }
}
