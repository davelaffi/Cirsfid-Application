package com.cirsf.model;

import java.io.Serializable;

public class Claim implements Serializable{

	
	
	private static final long serialVersionUID = 6020813717130704524L;
	
	private String id = "idClaim" + 1;
	private String matter;
	private String grade;
	private String type;
	private String object1;
	private String object2;
	private String seisedCountry;
	private String seisedCity;
	private String jurisdictionCivil;
	private String jurisdictionCivilCity;
	
	public String getJurisdictionCivil() {
		return jurisdictionCivil;
	}
	public void setJurisdictionCivil(String jurisdictionCivil) {
		this.jurisdictionCivil = jurisdictionCivil;
	}
	public String getJurisdictionCivilCity() {
		return jurisdictionCivilCity;
	}
	public void setJurisdictionCivilCity(String jurisdictionCivilCity) {
		this.jurisdictionCivilCity = jurisdictionCivilCity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getObject1() {
		return object1;
	}
	public void setObject1(String object) {
		this.object1 = object;
	}
	public String getObject2() {
		return object2;
	}
	public void setObject2(String object) {
		this.object2 = object;
	}
	public String getSeisedCountry() {
		return seisedCountry;
	}
	public void setSeisedCountry(String seisedCountry) {
		this.seisedCountry = seisedCountry;
	}
	public String getSeisedCity() {
		return seisedCity;
	}
	public void setSeisedCity(String seisedCity) {
		this.seisedCity = seisedCity;
	}
	
	
}
