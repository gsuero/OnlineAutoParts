package com.autoservices.web.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mailer {
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_PORT_NUMBER = "465";
	private static final String SMTP_AUTH_USER = "mail@mail.com";
	private static final String SMTP_AUTH_PWD  = "password";

	private String from;
	private String to;
	private String cc;
	private String subject;
	private String text;
	private String contentType = "text/html; charset=iso-8859-1";
	
	public Mailer(String from, String to, String subject, String text){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
	
	public Mailer(String from, String to, String cc, String subject, String text, String contentType){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.cc  = cc;
		this.contentType =  contentType;
	}
	
	public Mailer(String from, String to, String cc, String subject, String text){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.cc =  cc;
	}
	
	
	public synchronized void send() throws MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.port", SMTP_PORT_NUMBER);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtps.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", SMTP_HOST_NAME);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT_NUMBER);
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");


	    Authenticator auth = new SMTPAuthenticator();
	    Session session = Session.getDefaultInstance(props, auth);
		
		Message simpleMessage = new MimeMessage(session);
		
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		InternetAddress ccAddress = null;
		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
			if (cc != null && cc.length() > 0)
			ccAddress = new InternetAddress(cc);
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		try {
			simpleMessage.setFrom(fromAddress);
			simpleMessage.setRecipient(RecipientType.TO, toAddress);
			if (ccAddress != null)
				simpleMessage.setRecipient(RecipientType.CC, ccAddress);
			simpleMessage.setSubject(subject);
			simpleMessage.setContent(text, contentType);
			//simpleMessage.setText(text);
			Transport.send(simpleMessage);			
		} catch (MessagingException e) {
			throw e;
		}		
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}

}
