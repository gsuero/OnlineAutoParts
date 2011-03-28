package com.autoservice.ejbs.user;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.autoservice.entities.User;
import com.autoservice.entities.UserSession;
import com.autoservice.entities.UserType;
import com.autoservice.entities.Vehicle;
import com.autoservice.exceptions.AutoException;
import com.autoservice.exceptions.BadEmailException;
import com.autoservice.exceptions.InvalidUserAndPasswordException;
import com.autoservice.exceptions.NotExistsDepartmentException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservice.exceptions.NotExistsUserException;

@Local
public interface UserBeanLocal {

	public String sayHello(String name);

	public boolean changePassword(String login, String currentPassword,
			String newPassword) throws Exception;

	public List<UserType> getAllUserTypes();

	public List<UserType> getUserTypes(boolean admin, int status);

	public UserType getUserType(int type)
			throws NotExistsDepartmentException;

	public User getUser(String login) throws NotExistsUserException;
	
	public User getUser(int userid) throws NotExistsUserException;
	
	public int createUser(int userType, String login, String salutation,
			String firstName, char middleName, String lastName, Date birthDate,
			String password, String email, String referal, String habitualPit)
			throws NotExistsDepartmentException;

	public int createUser(User user);
	
	public boolean updateUser(String login, String salutation,
			String firstName, char middleName, String lastName, Date birthdate,
			String password, String email, String habitualPit) throws NotExistsUserException,
			BadEmailException;

	public void updateUser(User user) throws AutoException;
	
	public boolean isValidUserAndPassword(String login, String currentPassword)
			throws InvalidUserAndPasswordException;

//	public boolean createContact(int userId, String pais, String ciudad,
//			String sector, String calle, String numero, String apto,
//			String telefono, String celular);

	public boolean updateContact(String login, int pais, int ciudad,
			String sector, String calle, String numero, String apto,
			String telefono, String celular);
	
    public void createUserSession(UserSession userSession) throws AutoException;
    
    public UserSession getUserSession(String serie, String token) throws NotExistsException;
    
    public void updateUserSession(UserSession userSession) throws AutoException;
    
    public UserSession getUserSessionByUserId(String username) throws NotExistsException;
    
    public void deleteUserSession(UserSession userSession) throws AutoException;
    
    public void addVehicle(String login, Vehicle vehicle) throws AutoException;

    public Vehicle getVehicle(int vehicleid) throws NotExistsException, AutoException;
    
    public void updateVehicle(String login, Vehicle vehicle) throws AutoException;
    
    public void removeVehicle(Vehicle vehicle) throws AutoException;
    
    public void removeVehicle(String chassis) throws AutoException;
}