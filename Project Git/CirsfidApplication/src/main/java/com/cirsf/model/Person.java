package com.cirsf.model;

import java.io.Serializable;

public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5428778166728363196L;
	private String id = "idPerson";
	private String nature;
	private String role;
	private String type;
	private String work;
	private String cityWork;
	private String activityIn;
	private String cityActivity;
	private String domicile;
	private String cityDomicile;
	private String establishment;
	private String cityEstablishment;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getActivityIn() {
		return activityIn;
	}
	public void setActivityIn(String activityIn) {
		this.activityIn = activityIn;
	}
	public String getDomicile() {
		return domicile;
	}
	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	public String getEstablishment() {
		return establishment;
	}
	public void setEstablishment(String establishment) {
		this.establishment = establishment;
	}

	public String getCityWork() {
		return cityWork;
	}

	public void setCityWork(String cityWork) {
		this.cityWork = cityWork;
	}

	public String getCityActivity() {
		return cityActivity;
	}

	public void setCityActivity(String cityActivity) {
		this.cityActivity = cityActivity;
	}

	public String getCityDomicile() {
		return cityDomicile;
	}

	public void setCityDomicile(String cityDomicile) {
		this.cityDomicile = cityDomicile;
	}

	public String getCityEstablishment() {
		return cityEstablishment;
	}

	public void setCityEstablishment(String cityEstablishment) {
		this.cityEstablishment = cityEstablishment;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", nature=" + nature + ", role=" + role + ", type=" + type + ", work=" + work
				+ ", cityWork=" + cityWork + ", activityIn=" + activityIn + ", cityActivity=" + cityActivity
				+ ", domicile=" + domicile + ", cityDomicile=" + cityDomicile + ", establishment=" + establishment
				+ ", cityEstablishment=" + cityEstablishment + "]";
	}
	
	

	
	
	
	
}
