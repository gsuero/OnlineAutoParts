package com.autoservices.web.util;

import java.util.Date;

import com.autoservice.commons.Constants;
import com.autoservice.commons.security.PasswordBusiness;
import com.autoservice.ejbs.business.BusinessHelper;
import com.autoservice.ejbs.business.BusinessHelperLocal;
import com.autoservice.entities.MailTemplate;
import com.autoservice.entities.User;
import com.autoservice.entities.UserMailConfirmation;
import com.autoservice.exceptions.AutoException;
import com.autoservice.exceptions.ConfirmationMailException;
import com.autoservice.exceptions.NotExistsException;

public class UserConfirmationHelper {
	// user status: 1=activo, 0=inactivo, 2=PorConfirmar, 3=Borrado
	private User user;
	private String confirmationCodeRequested;
	private String emailAddressRequested;
	private String confirmationCode;
	private String emailAddress;

	public UserConfirmationHelper(User user) {
		setUser(user);
		setEmailAddress(getUser().getEmail());
	}

	public UserConfirmationHelper(String confirmationCodeRequested,
			String emailAddressRequested) {
		setEmailAddressRequested(emailAddressRequested);
		setConfirmationCodeRequested(confirmationCodeRequested);
	}
	public boolean confirmMail() throws NotExistsException, AutoException {
		boolean retorno = false;
		if (getConfirmationCodeRequested()!= null && getConfirmationCodeRequested().length() > 10 
				&& getEmailAddressRequested() != null && getEmailAddressRequested().length() > 0) {
			BusinessHelperLocal helper = new BusinessHelper();
			UserMailConfirmation mailConfirmation = helper.getMailConfirmation(getEmailAddressRequested(), getConfirmationCodeRequested());
			if (mailConfirmation.getUser().getStatus() == Constants.USER_STATUS_UNCONFIRM) {
				if (mailConfirmation.getConfirmado() == Constants.GLOBAL_STATUS_NOT_ACTIVATED) {
					mailConfirmation.setConfirmado(Constants.GLOBAL_STATUS_ACTIVATED);
					mailConfirmation.setFechaConfirmacion(Constants.COMMON_FULL_DATE_FORMAT.format(new Date()));
					mailConfirmation.getUser().setStatus(Constants.USER_STATUS_ACTIVE);
				} else {
					throw new AutoException("Codigo de confirmación inválido.");
				}
			} else {
				throw new AutoException("El correo " + getEmailAddressRequested() + " ya se encuentra confirmado...");
			}
			helper.updateMailConfirmation(mailConfirmation);
		} else {
			throw new NotExistsException();
		}
		
		return retorno;
	}
	public boolean sendConfirmationMail() throws ConfirmationMailException {
		boolean retorno =  false;
		try {
			if (getConfirmationCode() != null && getConfirmationCode().length() > 0) {
				//Mailer : send email
				BusinessHelperLocal helper = new BusinessHelper();
				MailTemplate template = helper.getMailTemplate("mailconfirmation".toUpperCase());
				String message = template.getTemplate()
									.replaceAll("@NOMBRES@", getUser().getFirstName() + " " + getUser().getLastName())
									.replaceAll("@URL@", Constants.WEB_URL+"")
									.replaceAll("@CONFIRMATION_CODE@", getConfirmationCode())
									.replaceAll("@EMAIL@", getEmailAddress());
				String subject = template.getSubject();
				String mailAddress = getEmailAddress();
				
				Mailer mail = new Mailer(Constants.MAIL_GLOBAL_FROM, 
										mailAddress, 
										subject, 
										message);
				mail.send();
			} else {
				throw new Exception("No se ha generado ningun codigo de confirmacion.");
			}
		} catch (Exception ex) {
			throw new ConfirmationMailException("Error enviando correo de confirmacion " + ex.getMessage());
		}
		return retorno;
	}
	public void generateConfirmationCode() throws ConfirmationMailException, AutoException {
		String date = new Date().getTime() + "";
		String mail = getEmailAddress();
		boolean generado = false;
		try {
			setConfirmationCode(byteArrayToHexString(computeHash(date + mail)));
			generado = true;
		} catch (Exception e) {
			try {
				setConfirmationCode(new PasswordBusiness(date + mail).getMD5());
				generado = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println(" previous exception :");
				e.printStackTrace();
				throw new ConfirmationMailException(ex.getMessage());
			}
		}
		if (generado) {
			try {
				BusinessHelperLocal helper = new BusinessHelper();
				UserMailConfirmation mailConfirmation = new UserMailConfirmation();
				mailConfirmation.setConfirmado(Constants.GLOBAL_STATUS_NOT_ACTIVATED);
				mailConfirmation.setConfirmationCode(getConfirmationCode());
				mailConfirmation.seteMail(getEmailAddress());
				mailConfirmation.setFechaGeneracion(Constants.COMMON_FULL_DATE_FORMAT.format(new Date()));
				mailConfirmation.setUser(getUser());
				//mailConfirmation.setUserId(getUser().getUserId());
				// persist the mailConfirmation
				helper.createMailConfirmation(mailConfirmation);
			} catch (AutoException ex) {
				throw new AutoException("Imposible crear ticked de confirmacion. " + ex.getMessage());
			}
		}

	}

	private byte[] computeHash(String x) throws Exception {
		java.security.MessageDigest d = null;
		d = java.security.MessageDigest.getInstance("SHA-1");
		d.reset();
		d.update(x.getBytes());
		return d.digest();
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmationCodeRequested() {
		return confirmationCodeRequested;
	}

	public void setConfirmationCodeRequested(String confirmationCodeRequested) {
		this.confirmationCodeRequested = confirmationCodeRequested;
	}

	public String getEmailAddressRequested() {
		return emailAddressRequested;
	}

	public void setEmailAddressRequested(String emailAddressRequested) {
		this.emailAddressRequested = emailAddressRequested;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
