package com.autoservice.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name = EjbConstants.TABLE_USERS)
@NamedQueries( {
		@NamedQuery(name = "User.findByLoginNPassword", query = "SELECT d FROM User d where d.login = :login and d.password = :password"),
		@NamedQuery(name = "User.findByLogin", query = "SELECT d FROM User d where d.login = :login"),
		@NamedQuery(name = "User.findByUserId", query = "SELECT d FROM User d where d.userId = :userId") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public User() {
		setVehicles(new ArrayList<Vehicle>());
		setContact(new Contact());
	}

	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	// @Column(name="user_type")

	@ManyToOne
	@JoinColumn(name = "user_type", nullable = false, updatable = false)
	private UserType userType;
	@Column(name = "login")
	private String login;
	@Column(name = "salutation")
	private String salutation;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private char middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "birthdate")
	private String birthdate;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "last_login")
	private String lastLogin;
	@Column(name = "referal")
	private String referal;
	@Column(name = "habitual_garage")
	private String habitualPit;
	@Column(name = "status")
	private int status;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "UserContactMap", joinColumns = { @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "contactid", referencedColumnName = "contactid", nullable = false, updatable = false) })
	private Contact contact;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "UserVehicleMap", joinColumns = { @JoinColumn(name = "userid", referencedColumnName = "userid") }, inverseJoinColumns = { @JoinColumn(name = "vehicleid", referencedColumnName = "vehicleid") })
	private List<Vehicle> vehicles;

	@Override
	public String toString() {
		String retorno = "User:" + getUserId() + " \n[type: "
				+ getUserType().getDesc() + "\nlogin: " + getLogin()
				+ "\nFirstName: " + getFirstName() + "\nLastName: "
				+ getLastName() + "\nbirthdate: " + getBirthdate()
				+ "\nlastlogin: " + getLastLogin() + "\nemail: " + getEmail()
				+ "\nreferal: " + getReferal() + "\nhabitualpit: "
				+ getHabitualPit();

		retorno += "\n" + getContact().toString();

		Iterator<Vehicle> it = vehicles.iterator();
		retorno += "\n   Vehicles: ";
		while (it.hasNext()) {
			Vehicle veh = it.next();
			retorno += veh.toString();
		}
		return retorno + "]";
	}

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

	public void setContact(Contact contact) {
		this.contact = contact;
		this.contact.setUser(this);
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		if (!getVehicles().contains(vehicle)) {
			this.getVehicles().add(vehicle);
		}
	}

	public void updateVehicle(Vehicle vehicle) {
		if (this.getVehicles() != null) {
			for (int a = 0; a < this.getVehicles().size(); a++) {
				Vehicle ve = this.getVehicles().get(a);
				if (vehicle.getId() == ve.getId()) {
					//this.getVehicles().add(a, vehicle);
					ve.setAnio(vehicle.getAnio());
					ve.setDescription(vehicle.getDescription());
					ve.setChassis(vehicle.getChassis());
					ve.setMarca(vehicle.getMarca());
					ve.setModelo(vehicle.getModelo());
					break;
				}
			}
		}
	}

	public void removeVehicle(String chassis) {
		if (this.getVehicles() != null) {
			for (int a = 0; a < this.getVehicles().size(); a++) {
				Vehicle ve = this.getVehicles().get(a);
				if (chassis.equalsIgnoreCase(ve.getChassis())) {
					this.getVehicles().remove(a);
					break;
				}
			}
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
