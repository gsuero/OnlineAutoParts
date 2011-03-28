package com.autoservice.ejbs.object;

import java.util.Arrays;

public class SPCGlassTintService {

	private String[] glasses;
	private String oscurity;
	private String quality;
	
	public SPCGlassTintService(String[] glasses, String oscurity,
			String quality) {
		super();
		this.glasses = glasses;
		this.oscurity = oscurity;
		this.quality = quality;
	}

	
	public String[] getGlasses() {
		return glasses;
	}


	public void setGlasses(String[] glasses) {
		this.glasses = glasses;
	}


	public String getOscurity() {
		return oscurity;
	}
	public void setOscurity(String oscurity) {
		this.oscurity = oscurity;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	@Override
	public String toString() {
		return "SPCGlassTintService [glasses=" + glasses
				+ ", oscurity=" + oscurity + ", quality=" + quality + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((oscurity == null) ? 0 : oscurity.hashCode());
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
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
		SPCGlassTintService other = (SPCGlassTintService) obj;
		if (glasses.length != other.glasses.length)
			return false;
		if (oscurity == null) {
			if (other.oscurity != null)
				return false;
		} else if (!oscurity.equals(other.oscurity))
			return false;
		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
			return false;
		return true;
	}



}
