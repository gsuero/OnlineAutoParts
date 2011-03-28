package com.autoservice.ejbs.object;

public class SPCTuneUpService {

	private String general;

	
	public SPCTuneUpService(String general) {
		super();
		this.general = general;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	@Override
	public String toString() {
		return "SPCTuneUpService [general=" + general + "]";
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
		SPCTuneUpService other = (SPCTuneUpService) obj;
		if (general == null) {
			if (other.general != null)
				return false;
		} else if (!general.equals(other.general))
			return false;
		return true;
	}
	
}
