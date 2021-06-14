package com.springboot.model;

public class CovidDataModel {
	private String province;
	private String country;
	private int currentCount;
	private int newCount;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	@Override
	public String toString() {
		return "CovidDataModel [province=" + province + ", country=" + country + ", currentCount=" + currentCount
				+ ", newCount=" + newCount + "]";
	}

}
