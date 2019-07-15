package com.cirsf.model;

import java.io.Serializable;

public class Contract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7537468447105986681L;
	private String id = "idContract" + 1;
	private String consideration;
	private String type;
	private String obligationTarget;
	private String obligationTargetUse;
	private String immovablePropertyCountry;
	private String immovablePropertyCity;
	private String placeOfProvision;
	private String placeOfProvisionCity;
	private String placeOfProvision2;
	private String placeOfProvisionCity2;
	
	
	
	public String getPlaceOfProvision2() {
		return placeOfProvision2;
	}
	public void setPlaceOfProvision2(String placeOfProvision2) {
		this.placeOfProvision2 = placeOfProvision2;
	}
	public String getPlaceOfProvisionCity2() {
		return placeOfProvisionCity2;
	}
	public void setPlaceOfProvisionCity2(String placeOfProvisionCity2) {
		this.placeOfProvisionCity2 = placeOfProvisionCity2;
	}
	public String getPlaceOfProvision() {
		return placeOfProvision;
	}
	public void setPlaceOfProvision(String placeOfProvision) {
		this.placeOfProvision = placeOfProvision;
	}
	public String getPlaceOfProvisionCity() {
		return placeOfProvisionCity;
	}
	public void setPlaceOfProvisionCity(String placeOfProvisionCity) {
		this.placeOfProvisionCity = placeOfProvisionCity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConsideration() {
		return consideration;
	}
	public void setConsideration(String consideration) {
		this.consideration = consideration;
	}
	
	public String getObligationTarget() {
		return obligationTarget;
	}
	public void setObligationTarget(String obligationTarget) {
		this.obligationTarget = obligationTarget;
	}
	public String getObligationTargetUse() {
		return obligationTargetUse;
	}
	public void setObligationTargetUse(String obligationTargetUse) {
		this.obligationTargetUse = obligationTargetUse;
	}
	public String getImmovablePropertyCountry() {
		return immovablePropertyCountry;
	}
	public void setImmovablePropertyCountry(String immovablePropertyCountry) {
		this.immovablePropertyCountry = immovablePropertyCountry;
	}
	public String getImmovablePropertyCity() {
		return immovablePropertyCity;
	}
	public void setImmovablePropertyCity(String immovablePropertyCity) {
		this.immovablePropertyCity = immovablePropertyCity;
	}
	
	
	
}
