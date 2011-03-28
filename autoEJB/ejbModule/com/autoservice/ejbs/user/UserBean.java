package com.autoservice.ejbs.user;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.autoservice.commons.security.PasswordBusiness;
import com.autoservice.entities.Contact;
import com.autoservice.entities.User;
import com.autoservice.entities.UserType;
import com.autoservice.util.EjbConstants;
import com.autoservice.util.JPAUtil;
import com.autoservice.exceptions.BadEmailException;
import com.autoservice.exceptions.InvalidUserAndPasswordException;
import com.autoservice.exceptions.NotExistsDepartmentException;
import com.autoservice.exceptions.NotExistsUserException;

/**
 * Session Bean implementation class UserBean
 * @author Garis M. Suero
 * 06/05/2010
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
    private User getUser(String login) throws NotExistsUserException
    {
    	User user = null;
		try {
			Query query = em.createNamedQuery("User.findByLogin");
			query.setParameter("login", login);
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
    private User getUser(int userid) throws NotExistsUserException
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
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        e.setBirthdate(dateFormat.format(birthDate));
	        e.setPassword(password);
	        e.setEmail(email);
	        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        e.setLastLogin(dateFormat.format(new Date()));
	        e.setReferal(referal);
	        e.setHabitualPit(habitualPit);
        } catch (NotExistsDepartmentException ex) { 
    		throw ex;
    	}
        return createUser(e);
    }
    public int createUser(User user) {
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
    
    public boolean updateUser(String login, String salutation, String firstName, char middleName, String lastName, Date birthdate, String password, String email) throws NotExistsUserException, BadEmailException {
    	boolean result = true;
    	try {
   	    	User user = getUser(login);
    		em.getTransaction().begin();
    		boolean modified = false;
   	    	if (password != null && password.length() > 0){
   	   	    	String newPassword = new PasswordBusiness(password).getMD5();
   	   	    	if (!newPassword.equals(user.getPassword())) {
   	   	    		user.setPassword(new PasswordBusiness(password).getMD5());
   	   	    		modified = true;
   	   	    	}
   	    	}
   	    	if (salutation!= null && salutation.length() > 0 && !salutation.equals(user.getSalutation())){
   	    		user.setSalutation(salutation);
   	    		modified = true;
   	    	}
   	    	
   	    	if (firstName!= null && firstName.length() > 0 && !firstName.equals(user.getFirstName()))  {
   	    		user.setFirstName(firstName);
   	    		modified = true;
   	    	}
   	    	
   	    	if (middleName != '-' && middleName != user.getMiddleName()) {
   	    		user.setMiddleName(middleName);
   	    		modified = true;
   	    	}
   	    	
   	    	if (lastName!= null && lastName.length() > 0 && !lastName.equals(user.getLastName())) {
   	    		user.setLastName(lastName);
   	    		modified = true;	
   	    	}
   	    	
   	    	if (email!= null && email.length() > 0 && !email.equals(user.getEmail())) {
   	    		user.setEmail(email);
   	    		modified = true;	
   	    	}
   	    	if (modified) {
   	    		em.persist(user);
   	    		em.merge(user);
   	    	}
    		em.getTransaction().commit();
    	} catch (NotExistsUserException er) {
    		throw er;
    	}catch (BadEmailException er) {
    		throw er;
    	} catch (Exception e) {
    		System.out.println("EXCEPCION!\n" + e.getMessage());
    		e.printStackTrace();
		}
    	return result;
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
    public com.autoservice.entities.UserType getUserType(int type) throws NotExistsDepartmentException {
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
    
    public boolean createContact(int userId, String pais, String ciudad, String sector, String calle, String numero, String apto, String telefono, String celular) {
    	try {
	        em.getTransaction().begin();
	        Contact e = new Contact();
	        
	        e.setUserId(userId);
	        e.setPais(pais);
	        e.setCiudad(ciudad);
	        e.setSector(sector);
	        e.setCalle(calle);
	        e.setNumero(numero);
	        e.setApto(apto);
	        e.setTelefono(telefono);
	        e.setCelular(celular);
	        
	        em.persist(e);
	        em.flush();
	        em.getTransaction().commit();
    	} catch (Exception ex) {
    		System.out.println("[EXCEPCION]!\n" + ex.getMessage());
    		ex.printStackTrace();
    		return false;
    	}
        return true;
    }
    
    public boolean updateContact(int userId, String pais, String ciudad, String sector, String calle, String numero, String apto, String telefono, String celular) {
    	try {
	        boolean updated = false;
    		Contact e = getContact(userId);
	        em.getTransaction().begin();
	        if (pais != null && !pais.equals(e.getPais())){
	        	e.setPais(pais);
	        	updated = true;
	        }
	        if (pais != null && !pais.equals(e.getPais())){
	        	e.setCiudad(ciudad);
	        	updated = true;
	        }
	        if (sector != null && !sector.equals(e.getSector())){
	        	e.setSector(sector);
	        	updated = true;
	        }
	        if (calle != null && !calle.equals(e.getCalle())){
	        	e.setCalle(calle);
	        	updated = true;
	        }
	        if (numero != null && !numero.equals(e.getNumero())){
	        	e.setNumero(numero);
	        }
	        if (apto != null && !apto.equals(e.getApto())){
	        	e.setApto(apto);
	        	updated = true;
	        }
	        if (telefono != null && !telefono.equals(e.getTelefono())){
	        	e.setTelefono(telefono);
	        	updated = true;
	        }
	        if (celular != null && !celular.equals(e.getCelular())){
	        	e.setCelular(celular);
	        	updated = true;
	        }
	        
	        if (updated) {
	        	em.persist(e);
	        	em.merge(e);
	        }
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
}
