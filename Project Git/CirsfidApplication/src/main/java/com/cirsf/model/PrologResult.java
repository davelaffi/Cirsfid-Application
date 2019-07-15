package com.cirsf.model;

import java.io.Serializable;

public class PrologResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String court;
	private String country;
	private String errors;
	
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
}
