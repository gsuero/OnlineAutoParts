package com.autoservices.web.util.lists;

public class Marcas {
	private String marcaCode;
	private String marcaName;
	
	public Marcas(String marcaCode, String marcaName){
		setMarcaCode(marcaCode);
		setMarcaName(marcaName);
	}

	public String getMarcaCode() {
		return marcaCode;
	}

	public void setMarcaCode(String marcaCode) {
		this.marcaCode = marcaCode;
	}

	public String getMarcaName() {
		return marcaName;
	}

	public void setMarcaName(String marcaName) {
		this.marcaName = marcaName;
	}
	
}
