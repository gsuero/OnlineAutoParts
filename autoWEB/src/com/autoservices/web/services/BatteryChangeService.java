package com.autoservices.web.services;

public class BatteryChangeService extends Service {
	
	private String batteryBrand;
	private String tipoBateria;
	private String referencia;
	public BatteryChangeService(String batteryBrand, String tipoBateria,
			String referencia) {
		super();
		this.batteryBrand = batteryBrand;
		this.tipoBateria = tipoBateria;
		this.referencia = referencia;
	}
	public String getBatteryBrand() {
		return batteryBrand;
	}
	public void setBatteryBrand(String batteryBrand) {
		this.batteryBrand = batteryBrand;
	}
	public String getTipoBateria() {
		return tipoBateria;
	}
	public void setTipoBateria(String tipoBateria) {
		this.tipoBateria = tipoBateria;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	@Override
	public String toString() {
		return "BatteryChangeService [batteryBrand=" + batteryBrand
				+ ", referencia=" + referencia + ", tipoBateria=" + tipoBateria
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((batteryBrand == null) ? 0 : batteryBrand.hashCode());
		result = prime * result
				+ ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result
				+ ((tipoBateria == null) ? 0 : tipoBateria.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BatteryChangeService other = (BatteryChangeService) obj;
		if (batteryBrand == null) {
			if (other.batteryBrand != null)
				return false;
		} else if (!batteryBrand.equals(other.batteryBrand))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (tipoBateria == null) {
			if (other.tipoBateria != null)
				return false;
		} else if (!tipoBateria.equals(other.tipoBateria))
			return false;
		return true;
	}
}
