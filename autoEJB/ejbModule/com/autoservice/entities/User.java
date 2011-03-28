package com.autoservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_USERS)
@NamedQueries({@NamedQuery(name = "User.findByLoginNPassword", query = "SELECT d FROM User d where d.login = :login and d.password = :password"),
			   @NamedQuery(name = "User.findByLogin", 		   query = "SELECT d FROM User d where d.login = :login"),
			   @NamedQuery(name = "User.findByUserId", 		   query = "SELECT d FROM User d where d.userId = :userId")})
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	//@Column(name="user_type")
	
	@ManyToOne
	@JoinColumn(name="user_type", nullable=false, updatable=false)
	private UserType userType;
	@Column(name="login")
	private String login;		 	 	 	 	 	 	 
	@Column(name="salutation")
	private String salutation;		 	 	 	 	 	 	 
	@Column(name="first_name")
	private String firstName;		 	 	 	 	 	 	 
	@Column(name="middle_name")
	private char middleName;		 	 	 	 	 	 	 
	@Column(name="last_name")
	private String lastName;
	@Column(name="birthdate")
	private String birthdate;
	@Column(name="password")
	private String password;		 	 	 	 	 	 	 
	@Column(name="email")
	private String email;		
	@Column(name="last_login")
	private String lastLogin;
	@Column(name="referal")
	private String referal;
	@Column(name="habitual_garage")
	private String habitualPit;

	@OneToOne
	@JoinColumn(name="userid", nullable=true, updatable=true)
	private Contact contact;
	
	@OneToMany
	@JoinColumn(name="userid", nullable=true, updatable=true)
	private Vehicles vehicles;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public char getMiddleName() {
		return middleName;
	}
	public void setMiddleName(char middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String string) {
		this.lastLogin = string;
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
	public void setContacts(Contact contact) {
		this.contact = contacts;
	}
	public Vehicles getVehicles() {
		return vehicles;
	}
	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}
	@Override
	public String toString() {
		return "User:" + getUserId() + " [type: " + getUserType().getDesc() + "\nlogin: "
				+ getLogin() + "\nFirstName: " + getFirstName() + "\nLastName: " + getLastName() +
				"\nlastlogin: " + getLastLogin() + "\nemail: " + getEmail() + "]";
	}
}
