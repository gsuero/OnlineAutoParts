package com.autoservice.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Constants {

	public final String JDBC_MYSQL = "jdbc/MyAutoService";
	public final String WEB_URL = "http://localhost:8084/autoWEB/";
	
	public final String REQUEST_SUCCESSFUL_PARAMETER_NAME = "SUCCESS_PARAM";
	
	public final int DEFAULT_USER_TYPE_CUSTOMERS = 0;
	public final int MAX_AGE_IN_DAYS_BACK = 6120;
	public final int TAMANO_MINIMO_PASSWORD = 5;
	public final int TAMANO_MAXIMO_PASSWORD = 25;

	// caracteres..
	public final String CHAR_UNICODE_ACUTE_a = "\00e1"; // -> á
	public final String CHAR_UNICODE_ACUTE_e = "\00e9"; // -> é
	public final String CHAR_UNICODE_ACUTE_i = "\00ed"; // -> í
	public final String CHAR_UNICODE_ACUTE_o = "\00f3"; // -> ó
	public final String CHAR_UNICODE_ACUTE_u = "\00fa"; // -> ú

	public final String CHAR_UNICODE_ACUTE_A = "\00c1"; // -> Á
	public final String CHAR_UNICODE_ACUTE_E = "\00c9"; // -> É
	public final String CHAR_UNICODE_ACUTE_I = "\00cd"; // -> Í
	public final String CHAR_UNICODE_ACUTE_O = "\00d3"; // -> Ó
	public final String CHAR_UNICODE_ACUTE_U = "\00da"; // -> Ú

	public final String CHAR_UNICODE_TILDE_n = "\00f1"; // -> ñ
	public final String CHAR_UNICODE_TILDE_N = "\00d1"; // -> Ñ
	public final DateFormat BIRTH_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public final DateFormat COMMON_DATE_FORMAT = new SimpleDateFormat("MM/dd/yy"); // 10/22/84
	public final DateFormat COMMON_FULL_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public final int GLOBAL_STATUS_ACTIVATED = 1;
	public final int GLOBAL_STATUS_NOT_ACTIVATED = 0;
	
	// users constants // user status: 1=activo, 0=inactivo, 2=PorConfirmar, 3=Borrado
	public final int USER_STATUS_INACTIVE = 0;
	public final int USER_STATUS_ACTIVE = 1;
	public final int USER_STATUS_UNCONFIRM = 2;
	public final int USER_STATUS_DELETED = 3;
	
	public final String GLOBAL_YES_RESPONSE = "S";
	public final String GLOBAL_NO_RESPONSE = "N";
	
	public final String MAIL_GLOBAL_FROM = "garis.suero@gmail.com";
	
	public final String REQUEST_KEY_ERROR_MSG = "ERROR_MESSAGE";
	
	public final String GLOBAL_USER_SESSION_KEY = "USER";
	public final String GLOBAL_REMEMBER_SESSION_KEY = "NO_REMEMBER";
	public final String GLOBAL_ITEMS_ADDED_SESSION_KEY = "ITEMS_ADDED_TO_CART";
	public final String GLOBAL_CART_COUNT_SESSION_KEY = "ITEMS_COUNT_CART";
	public final String GLOBAL_CART_SESSION_KEY = "SHOPPING_CART";
	
	public final int DEFAULT_COUNTRY = 1;

	public static String MAIL_BEAN="mailbean";
	
	public static String FROM_MAIL="deepak@localhost";
}
