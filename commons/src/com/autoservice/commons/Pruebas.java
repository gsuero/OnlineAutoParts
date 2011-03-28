package com.autoservice.commons;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class Pruebas {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
			String fecha = "08/04/2010";
			String horario = "5:00 PM";
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
			String fe = dateFormat.format(new Date(fecha+" " + horario));
			Date date = new Date(fecha+" " + horario);
			
			System.out.println(date.toString());
			
	}

	  public static boolean isValidEmailAddress(String aEmailAddress){
		    if (aEmailAddress == null) return false;
		    boolean result = true;
		    try {
		      new InternetAddress(aEmailAddress);
		      if (!hasNameAndDomain(aEmailAddress) ) {
		        result = false;
		      }
		    }
		    catch (AddressException ex){
		      result = false;
		    }
		    return result;
		  }


	  private static boolean hasNameAndDomain(String aEmailAddress){
	    String[] tokens = aEmailAddress.split("@");
	    return tokens.length == 2 && (tokens[0] != null && tokens[0].length() > 0) && (tokens[1] != null && tokens[1].length() > 0) ;
	  }

}
