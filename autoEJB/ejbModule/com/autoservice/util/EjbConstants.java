package com.autoservice.util;

public interface EjbConstants {

	// tables
	public final String TABLE_USER_TYPES = "USER_TYPES";
	public final String TABLE_MULTIPLE_TYPES = "tipos_multiples";
	public final String TABLE_CONTACTS = "CONTACTS";
	public final String TABLE_VEHICLES = "VEHICLES";
	public final String TABLE_USERS = "AUTO_USERS";
	public final String TABLE_USER_CONFIRMATION = "user_confirmations";
	public final String TABLE_PIEZAS = "piezas";
	public final String TABLE_PIEZAS_CATEGORY = "piezas_category";
	public final String TABLE_MAIL_TEMPLATES = "mail_templates";
	public final String TABLE_USER_SESSION = "user_session";
	public final String TABLE_PAIS = "paises";
	public final String TABLE_CIUDAD = "ciudades";
	public final String TABLE_HOLIDAYS = "holydays";
	public final String TABLE_VEHICULO_MARCA = "vehiculo_marca";
	public final String TABLE_VEHICULO_MODELO = "vehiculo_modelo";
	public final String TABLE_SUPLIDOR = "suplidores";
	public final String TABLE_ITEM_PRICES = "itemprices";
	public final String TABLE_SUPPLIER_PER_ORDER = "suppliersperorder";
	public final String TABLE_ORDER_ITEM = "order_item";

	
	// messages
	public final String MESSAGE_NO_DATA_FOUND = "No se encontraron datos";
	public final String MESSAGE_NO_DEPARMENT_EXISTS = "Depertamento inexistente";
	public final String MESSAGE_NO_USER_EXISTS = "Usuario inexistente";
	public final String MESSAGE_NOT_EXISTS = " inexistente";
	public final String MESSAGE_NOT_VALID_USER_AND_PASSWORD = "Usuario o contraseña inválida";
	public final String MESSAGE_UNKNOWN_ERROR = "Error desconocido";
	public final String MESSAGE_APPLICATION_ERROR = "Error en la aplicacion: ";
	
	
}
