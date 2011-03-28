package com.autoservice.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name=EjbConstants.TABLE_USER_SESSION)
@NamedQueries({@NamedQuery(name = "UserSession.findByAll", query = "SELECT d FROM UserSession d where d.serie=:serie and d.token=:token"),
	@NamedQuery(name = "UserSession.findBySerie", query = "SELECT d FROM UserSession d where d.serie=:serie")})
public class UserSession implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name="serie")
	private String serie;		 	 	 	 	 	 	 
	@Column(name="token")
	private String token;
	@OneToOne 
    @JoinColumn(name="username", referencedColumnName="login")
    @Id
    private User user;
	
	public UserSession(){
		super();
	}
	
	@Override
	public String toString() {
		return "";
		/*return "UserType:" + getType() + " [desc: " + getDesc() + "\nadmin: "
				+ getAdmin() + "\nstatus: " + getStatus()+ "]";
				*/
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
