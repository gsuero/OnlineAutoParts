package com.autoservices.web.util.lists;

public class Ciudad {
	private String cityCode;
	private String cityName;
	
	public Ciudad(String cityCode, String cityName){
		this.cityCode = cityCode;
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
