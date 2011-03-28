package com.autoservice.ejbs.user;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.autoservice.entities.User;
import com.autoservice.entities.UserType;
import com.autoservice.exceptions.BadEmailException;
import com.autoservice.exceptions.InvalidUserAndPasswordException;
import com.autoservice.exceptions.NotExistsDepartmentException;
import com.autoservice.exceptions.NotExistsUserException;

@Local
public interface UserBeanLocal {

	public String sayHello(String name);

	public boolean changePassword(String login, String currentPassword,
			String newPassword) throws Exception;

	public List<UserType> getAllUserTypes();

	public List<UserType> getUserTypes(boolean admin, int status);

	public com.autoservice.entities.UserType getUserType(int type)
			throws NotExistsDepartmentException;

	public int createUser(int userType, String login, String salutation,
			String firstName, char middleName, String lastName, Date birthDate,
			String password, String email, String referal, String habitualPit)
			throws NotExistsDepartmentException;

	public int createUser(User user);
	
	public boolean updateUser(String login, String salutation,
			String firstName, char middleName, String lastName, Date birthdate,
			String password, String email) throws NotExistsUserException,
			BadEmailException;

	public boolean isValidUserAndPassword(String login, String currentPassword)
			throws InvalidUserAndPasswordException;

	public boolean createContact(int userId, String pais, String ciudad,
			String sector, String calle, String numero, String apto,
			String telefono, String celular);

	public boolean updateContact(int userId, String pais, String ciudad,
			String sector, String calle, String numero, String apto,
			String telefono, String celular);
}