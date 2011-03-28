package com.autoservices.web.services;

public class TuneUpService extends Service {

	private String general = "Servicio general de tunning";

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	@Override
	public String toString() {
		return "TuneUpService [general=" + general + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((general == null) ? 0 : general.hashCode());
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
		TuneUpService other = (TuneUpService) obj;
		if (general == null) {
			if (other.general != null)
				return false;
		} else if (!general.equals(other.general))
			return false;
		return true;
	}
	
	
}
