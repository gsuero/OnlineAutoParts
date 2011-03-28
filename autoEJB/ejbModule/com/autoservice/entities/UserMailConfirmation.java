package com.autoservice.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_USER_CONFIRMATION)
@NamedQueries({@NamedQuery(name = "userMailConfirmation.findByEmailNConfirmationCode", query = "SELECT d FROM UserMailConfirmation d where d.eMail=:email and d.confirmationCode=:confirmationcode and d.confirmado=0")})
public class UserMailConfirmation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="confirmationid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int confirmationId;
	/*@Column(name="userId")
	private int userId;*/		 	 	 	 	 	 	 
	@Column(name="email")
	private String eMail;		 	 	 	 	 	 	 
	@Column(name="confirmation_code")
	private String confirmationCode;
	@Column(name="fecha_generacion")
	private String fechaGeneracion;
	@Column(name="fecha_confirmacion")
	private String fechaConfirmacion;
	@Column(name="confirmado")
	private int confirmado;
	@OneToOne 
    @JoinColumn(name="userid") 
    private User user;
	
	public UserMailConfirmation(){
		super();
	}
	
	@Override
	public String toString() {
		return "";
		/*return "UserType:" + getType() + " [desc: " + getDesc() + "\nadmin: "
				+ getAdmin() + "\nstatus: " + getStatus()+ "]";
				*/
	}

	public int getConfirmationId() {
		return confirmationId;
	}

	public void setConfirmationId(int confirmationId) {
		this.confirmationId = confirmationId;
	}
/*
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
*/
	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public String getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(String fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public int getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(int confirmado) {
		this.confirmado = confirmado;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
