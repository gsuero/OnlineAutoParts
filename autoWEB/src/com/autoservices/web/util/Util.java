package com.autoservices.web.util;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.autoservice.commons.Constants;
import com.autoservice.ejbs.business.BusinessHelper;
import com.autoservice.ejbs.business.BusinessHelperLocal;
import com.autoservice.entities.Holiday;
import com.autoservice.entities.MultipleType;
import com.autoservice.entities.Pais;
import com.autoservice.entities.vehicles.Marca;
import com.autoservice.exceptions.NotExistsException;
import com.autoservices.web.util.lists.Ciudad;
import com.autoservices.web.util.lists.LabelValueBean;
import com.autoservices.web.util.lists.Marcas;

public class Util {
		private static ArrayList<com.autoservices.web.util.lists.Ciudad> ciudades;
		private static ArrayList<Marcas> marcas;
		private static ArrayList<String> aniosCarros;
		private static Properties props;
		private static ArrayList<LabelValueBean> serviceTimes;
		private static HashMap<String, Integer> holidays;
		public static final String TYPE_OIL = "oil";
		public static final String TYPE_OIL_BRANDS = "oilBrand";
		public static final String TYPE_TINT_DARKNESS = "tintDarkness";
		public static final String TYPE_TINT_QUALITY = "tintQuality";
		public static final String TYPE_TINT_CRYSTALS = "cristales";
		
		public static final String TYPE_BATERY_TYPE = "tipoBateria";
		public static final String TYPE_BATERY_BRAND = "marcaBateria";

		public static final String TYPE_TIRE_BRAND = "tireBrand";
		public static final String TYPE_TIRE_SIZE = "tireSizes";

		public static ArrayList<LabelValueBean> getServicesTimes() throws NotExistsException {
			if (serviceTimes == null || serviceTimes.size() < 1) {
				serviceTimes = new ArrayList<LabelValueBean>();
				serviceTimes.add(new LabelValueBean("8:00 AM", "8:00:00 AM"));
				serviceTimes.add(new LabelValueBean("8:30 AM", "8:30:00 AM"));
				serviceTimes.add(new LabelValueBean("9:00 AM", "9:00:00 AM"));
				serviceTimes.add(new LabelValueBean("9:30 AM", "9:30:00 AM"));
				serviceTimes.add(new LabelValueBean("10:00 AM", "10:00:00 AM"));
				serviceTimes.add(new LabelValueBean("10:30 AM", "10:30:00 AM"));
				serviceTimes.add(new LabelValueBean("11:00 AM", "11:00:00 AM"));
				serviceTimes.add(new LabelValueBean("11:30 AM", "11:30:00 AM"));
				serviceTimes.add(new LabelValueBean("2:00 PM", "2:00:00 PM"));
				serviceTimes.add(new LabelValueBean("2:30 PM", "2:30:00 PM"));
				serviceTimes.add(new LabelValueBean("3:00 PM", "3:00:00 PM"));
				serviceTimes.add(new LabelValueBean("3:30 PM", "3:30:00 PM"));
				serviceTimes.add(new LabelValueBean("4:00 PM", "4:00:00 PM"));
				serviceTimes.add(new LabelValueBean("4:30 PM", "4:30:00 PM"));
				serviceTimes.add(new LabelValueBean("5:00 PM", "5:00:00 PM"));
				serviceTimes.add(new LabelValueBean("5:30 PM", "5:30:00 PM"));
				serviceTimes.add(new LabelValueBean("6:00 PM", "6:00:00 PM"));
				serviceTimes.add(new LabelValueBean("6:30 PM", "6:30:00 PM"));
				serviceTimes.add(new LabelValueBean("7:00 PM", "7:00:00 PM"));
				serviceTimes.add(new LabelValueBean("7:30 PM", "7:30:00 PM"));
				serviceTimes.add(new LabelValueBean("8:00 PM", "8:00:00 PM"));
				serviceTimes.add(new LabelValueBean("8:30 PM", "8:30:00 PM"));
			}
			return serviceTimes;
		}

		public static List<LabelValueBean> getMultipleTypesByType(String type) throws NotExistsException {
			List<LabelValueBean> retorno = new ArrayList<LabelValueBean>();
			
			BusinessHelperLocal helper = new BusinessHelper();
			List<MultipleType> list = helper.getMultipleType(type);
			
			Iterator<MultipleType> iter = list.iterator();
			while(iter.hasNext()) {
				MultipleType typ = iter.next();
				retorno.add(new LabelValueBean(typ.getDescription(), typ.getDescription()));
			}
			
			return retorno;
			
		}
		
		public static List<LabelValueBean> getHolidays(int year) throws NotExistsException {
			List<LabelValueBean> retorno = new ArrayList<LabelValueBean>();
			
			if (holidays == null || holidays.size() < 1 || !holidays.containsValue(year)) {
				BusinessHelperLocal helper = new BusinessHelper();
				List<Holiday> holis = helper.getHolidays(year);
				//8-16-2010
				Iterator<Holiday> iter = holis.iterator();
				holidays = new HashMap<String, Integer>();
				while(iter.hasNext()) {
					Holiday feriado = iter.next();
					holidays.put(feriado.getMonth() + "-" + feriado.getDay() + "-" + feriado.getYear(), year);
				}
			}
			
			if (holidays != null && holidays.size() > 0) {
				Iterator<String> it = holidays.keySet().iterator();
				while(it.hasNext()) {
					String day = it.next();
					retorno.add(new LabelValueBean(day, day));
				}
			}
			return retorno;
			
		}
		
		public static ArrayList<String> getCarYears() {
			if (aniosCarros == null || aniosCarros.size() <1) {
				Calendar cal = Calendar.getInstance();
				aniosCarros = new ArrayList<String>();
				aniosCarros = new ArrayList<String>();
				int maxYear = cal.get(Calendar.YEAR)+1;
				int minYear = cal.get(Calendar.YEAR)-40;
				while(maxYear > minYear) {
					aniosCarros.add((maxYear--)+"");
				}
			}
			return aniosCarros;
		}

		
		public static ArrayList<Marcas> getMarcas() throws NotExistsException {
			if (marcas == null || marcas.size() < 1) {
				BusinessHelperLocal helper = new BusinessHelper();
				List<Marca> marcasList = helper.getMarcas();
				marcas = new ArrayList<Marcas>();
				if (marcasList != null) {
					Iterator<Marca> it = marcasList.iterator();
					while(it.hasNext()) {
						Marca marca = it.next();
						marcas.add(new Marcas(marca.getMarcaid()+"",marca.getMarca()));
					}
				}
			}
			return marcas;
		}
		
		public static ArrayList<com.autoservices.web.util.lists.Ciudad> getCiudades() throws NotExistsException {
			if (ciudades == null || ciudades.size() < 1) {
				BusinessHelperLocal helper = new BusinessHelper();
				Pais pais = helper.getCountry(Constants.DEFAULT_COUNTRY);
				List<com.autoservice.entities.Ciudad> cities = helper.getCiudades(pais);
				ciudades = new ArrayList<com.autoservices.web.util.lists.Ciudad>();
				if (cities != null) {
					Iterator<com.autoservice.entities.Ciudad> it = cities.iterator();
					while(it.hasNext()) {
						com.autoservice.entities.Ciudad city = it.next();
						ciudades.add(new com.autoservices.web.util.lists.Ciudad(city.getCiudadid()+"",city.getCiudad()));
					}
				}
				else {
					ciudades.add(new com.autoservices.web.util.lists.Ciudad("1","Santo Domingo"));
				}
			}
			return ciudades;
		}
		public static String getCiudad(String ciudadId) throws NotExistsException {
			if (ciudades == null || ciudades.size() < 1) 
				getCiudades();
			
			String ciudadLabel = "";
			Iterator<Ciudad> it = ciudades.iterator();
			while(it.hasNext()) {
				Ciudad city = it.next();
				if (ciudadId.equalsIgnoreCase(city.getCityCode())) {
					ciudadLabel = city.getCityName();
					break;
				}
			}
			return ciudadLabel;
		}

		public static boolean isNumber(String val) {
			try {
				Integer.parseInt(val);
			} catch (Exception ex) {
				return false;
			}
			return true;
		}
		
		public static boolean isChassisValid(String chassis) {
			if (chassis != null && chassis.length() > 0 && chassis.length() < 17) {
				String invalidChars = "ioqIOQ";
				char[] chars = invalidChars.toCharArray();
				
				for(int a=0; a<chars.length; a++) {
					if (chassis.indexOf(chars[a]) >= 0) {
						return false;
					}
				}
				
				return true;
			}
			
			return false;
		}
		public static boolean isMarcaValid(String marca) {
			if (marca != null && marca.length() > 0 && marca.length() <= 65) {
				return true;
			}
			return false;
		}
		public static boolean isModeloValid(String marca, String modelo) {
			if (modelo != null && modelo.length() > 0 && modelo.length() <= 65) {
				return true;
			}
			return false;
		}
		public static boolean isAnioValid(String anio) {
			if (anio != null && anio.length() > 0 && isNumber(anio) && anio.length() == 4) {
				return true;
			}
			return false;
		}
		public static boolean isCarNameValid(String carName) {
			if (carName != null && carName.length() > 0 && carName.length() <= 150) {
				return true;
			}
			return false;
		}
		
		public static boolean isSectorValid(String sector) {
			if (sector != null && sector.length() > 0 && sector.length() <= 255) {
				return true;
			}
			return false;
		}
		public static boolean isNumeroContacto(String sector) {
			if (sector != null && sector.length() > 0 && sector.length() <= 10) {
				return true;
			}
			return false;
		}
		public static boolean isCalleValid(String calle) {
			if (calle != null && calle.length() > 0 && calle.length() <= 100) {
				return true;
			}
			return false;
		}
		
		public static boolean isAptoValid(String apto) {
			if (apto != null && apto.length() > 0 && apto.length() <= 255) {
				return true;
			}
			return false;
		}
		
		public static boolean isFirstNameValid(String firstName) {
			if (firstName != null && firstName.length() > 0 && firstName.length() <= 60) {
				return true;
			}
			return false;
		}
		public static boolean isLastNameValid(String lasttName) {
			if (lasttName != null && lasttName.length() > 0 && lasttName.length() <= 60) {
				return true;
			}
			return false;
		}
		
		public static boolean isMiddleNameValid(String middleName) {
			if (middleName != null && middleName.length() == 1) {
				return true;
			}
			return false;
		}
		
		public static boolean isSalutationValid(String salutation) {
			if (salutation != null && salutation.length() <= 5) {
				return true;
			}
			return false;
		}
		
		public static boolean isValidDateStr(String date) {
			try {
					DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT); // YYYY-MM-DD
					df.setLenient(false);   // this is important!
					df.parse(date);
			}
			catch (ParseException e) {
				return false;
			}
			catch (IllegalArgumentException e) {
				return false;
			}
			return true;
		}

		public static boolean isBirthDateValid(Date fecha) {
			long purgeTime = System.currentTimeMillis() - (Constants.MAX_AGE_IN_DAYS_BACK * 24 * 60 * 60 * 1000);
			return fecha.before(new Date(purgeTime));
		}
		public static boolean isBirthDateValid(String fecha) {
			if (fecha != null && fecha.length() > 0 && isValidDateStr(fecha)) {
				long purgeTime = System.currentTimeMillis() - (Constants.MAX_AGE_IN_DAYS_BACK * 24 * 60 * 60 * 1000);
				try {
					return DateFormat.getDateInstance(DateFormat.SHORT).parse(fecha).before(new Date(purgeTime));
				} catch (ParseException e) {
					return false;
				}
			}
			return false;
		}
		public static boolean isPasswordValid(String password, String confirmation) {
			if (password != null && confirmation != null && password.equalsIgnoreCase(confirmation)) {
				return true;
			}
			return false;
		}
		
		public static boolean isPasswordValid(String password) {
			if (password != null && password.length() <= Constants.TAMANO_MAXIMO_PASSWORD && password.length() >= Constants.TAMANO_MINIMO_PASSWORD) {
				return true;
			}
			return false;
		}
		
		public static boolean isReferalValid(String referak) {
			if (referak != null && referak.length() <= 255) {
				return true;
			}
			return false;
		}
		
		public static boolean isHabitualPitValid(String habitualPit) {
			if (habitualPit != null && habitualPit.length() <= 100) {
				return true;
			}
			return false;
		}
		public static boolean isTelefonoValid(String phoneNumber){  
			boolean isValid = false;  
			/* Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn 
			    ^\\(? : May start with an option "(" . 
			    (\\d{3}): Followed by 3 digits. 
			    \\)? : May have an optional ")" 
			    [- ]? : May have an optional "-" after the first 3 digits or after optional ) character. 
			    (\\d{3}) : Followed by 3 digits. 
			     [- ]? : May have another optional "-" after numeric digits. 
			     (\\d{4})$ : ends with four digits. 
			 
			         Examples: Matches following <a href="http://mylife.com">phone numbers</a>: 
			         (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890 
			 
			 */  
			 String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";  
			 CharSequence inputStr = phoneNumber;  
			 Pattern pattern = Pattern.compile(expression);  
			 Matcher matcher = pattern.matcher(inputStr);  
			 if(matcher.matches()){  
				 isValid = true;  
			 }  
			 return isValid;  
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
		  
		public static boolean isNumeric(String number){  
			boolean isValid = false;  
			   /*Number: A numeric value will have following format: 
			            ^[-+]?: Starts with an optional "+" or "-" sign. 
			        [0-9]*: May have one or more digits. 
			       \\.? : May contain an optional "." (decimal point) character. 
			       [0-9]+$ : ends with numeric digit. 
			   */  
			     
			   //Initialize reg ex for numeric data.  
		   String expression = "^[-+]?[0-9]*\\.?[0-9]+$";  
		   CharSequence inputStr = number;  
		   Pattern pattern = Pattern.compile(expression);  
		   Matcher matcher = pattern.matcher(inputStr);  
		   if(matcher.matches()){  
			   isValid = true;  
		   }  
		   return isValid;  
	  }

		public static Properties getProps() {
			return props;
		}

		public static void setProps(Properties props) {
			Util.props = props;
		}
		public static String getProperty(String name) {
			if (getProps() == null) {
			    try {
			    	setProps(new Properties());
			        URL url = ClassLoader.getSystemResource("auto.properties");
			        props.load(url.openStream());

			    } catch (IOException e) {
			    }
			}
			return props.getProperty(name);
		}

		public static int[] convertStringArraytoIntArray(String[] sarray) throws Exception {
			if (sarray != null) {
				int intarray[] = new int[sarray.length];
				for (int i = 0; i < sarray.length; i++) {
					intarray[i] = Integer.parseInt(sarray[i]);
				}
				return intarray;
			}
			return null;
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//rounding a double number up to 2 decimal places
		public double RoundDoubleTo2DecimalPlaces(double Rval, int Rpl){
			
			double p = (double)Math.pow(10,Rpl);
			Rval = Rval * p;
			  double tmp = Math.round(Rval);
			  double roundedValue=(double)tmp/p;	
			
			return roundedValue;
		}
		
		
		//change string date to Date  
		public  Date changeStringtoDate(String dob) {
			Date fdob = null;
			try {
				fdob = sdf.parse(dob);
			} catch (ParseException e) {
			}
			return fdob;
		}
		//change  Date  to string date
		public  String changeDatetoString(Date dob) {
			String fdob = null;
			try {
				fdob = sdf.format(dob);
			} catch (Exception e) {
			}
			return fdob;
		}

}
