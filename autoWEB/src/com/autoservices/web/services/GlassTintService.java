package com.autoservices.web.services;

import java.util.Arrays;

public class GlassTintService extends Service {

	private String[] glasses;
	private String oscurity;
	private String quality;
	
	public GlassTintService(String[] glassQuantity, String oscurity, String quality) {
		super();
		this.glasses = glassQuantity;
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
		final int maxLen = 10;
		return "GlassTintService [glasses="
				+ (glasses != null ? Arrays.asList(glasses).subList(0,
						Math.min(glasses.length, maxLen)) : null)
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
		GlassTintService other = (GlassTintService) obj;
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
