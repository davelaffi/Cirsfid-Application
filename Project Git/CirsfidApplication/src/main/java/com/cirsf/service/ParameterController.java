package com.cirsf.service;

import java.util.ArrayList;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.cirsf.model.Cause;
import com.cirsf.model.Person;

public class ParameterController extends MultiAction {
   
    
    public Event addPersonToList (RequestContext context) throws Exception{
 
    	Cause c = (Cause) context.getActiveFlow().getApplicationContext().getBean("cause");
    	ArrayList<Person> lista = c.getPersons(); 
    
    	Person p = new Person();
    	p.setNature(context.getRequestParameters().get("nature"));
    	p.setRole(context.getRequestParameters().get("role"));
    	p.setType(context.getRequestParameters().get("type"));
    	p.setWork(context.getRequestParameters().get("work"));
    	p.setCityWork(context.getRequestParameters().get("cityWork"));
    	p.setActivityIn(context.getRequestParameters().get("activityIn"));
    	p.setCityActivity(context.getRequestParameters().get("cityActivity"));
    	p.setDomicile(context.getRequestParameters().get("domicile"));
    	p.setCityDomicile(context.getRequestParameters().get("cityDomicile"));
    	p.setEstablishment(context.getRequestParameters().get("establishment"));
    	p.setCityEstablishment(context.getRequestParameters().get("cityEstablishment"));
    	
    	
    	//Per l'id prendo dal form, se non ha inserito id a default setto quello
    	//della lista + 1
    	if(context.getRequestParameters().get("idPerson").isEmpty()) {

    		String id = p.getId();
    		p.setId(id + (lista.size() + 1));
    	}
    	else {
    		
    		p.setId(context.getRequestParameters().get("idPerson"));
    	}
    	
    	lista.add(p);
    	c.setPersons(lista);
    	
        return success();
    }
    
    public Event addClaimParameter (RequestContext context) throws Exception {
		
    	Cause c = (Cause) context.getActiveFlow().getApplicationContext().getBean("cause");
    	
    	c.getClaim().setMatter(context.getRequestParameters().get("matter"));
    	c.getClaim().setGrade(context.getRequestParameters().get("grade"));
    	c.getClaim().setType(context.getRequestParameters().get("type"));
    	c.getClaim().setObject1(context.getRequestParameters().get("object1"));
    	c.getClaim().setObject2(context.getRequestParameters().get("object2"));
    	c.getClaim().setSeisedCountry(context.getRequestParameters().get("seisedCountry"));
    	c.getClaim().setSeisedCity(context.getRequestParameters().get("seisedCity"));
    	c.getClaim().setJurisdictionCivil(context.getRequestParameters().get("jurisdictionCivil"));
    	c.getClaim().setJurisdictionCivilCity(context.getRequestParameters().get("jurisdictionCivilCity"));
    	
    	if(!context.getRequestParameters().get("idClaim").isEmpty()) {

    		c.getClaim().setId(context.getRequestParameters().get("idClaim"));
        	
    	}
    	
    	return success();
    }
    
    public Event addContractParameter (RequestContext context) throws Exception {
		
    	Cause c = (Cause) context.getActiveFlow().getApplicationContext().getBean("cause");
    	
    	c.getContract().setConsideration(context.getRequestParameters().get("consideration"));
    	c.getContract().setObligationTarget(context.getRequestParameters().get("obligationTarget"));
    	c.getContract().setObligationTargetUse(context.getRequestParameters().get("obligationTargetUse"));
    	c.getContract().setImmovablePropertyCountry(context.getRequestParameters().get("immovablePropertyCountry"));
    	c.getContract().setImmovablePropertyCity(context.getRequestParameters().get("immovablePropertyCity"));
    	c.getContract().setType(context.getRequestParameters().get("type"));
    	c.getContract().setPlaceOfProvision(context.getRequestParameters().get("placeOfProvision"));
    	c.getContract().setPlaceOfProvisionCity(context.getRequestParameters().get("placeOfProvisionCity"));
    	c.getContract().setPlaceOfProvision2(context.getRequestParameters().get("placeOfProvision2"));
    	c.getContract().setPlaceOfProvisionCity2(context.getRequestParameters().get("placeOfProvisionCity2"));
    	
    	if(!context.getRequestParameters().get("idContract").isEmpty()) {

    		c.getContract().setId(context.getRequestParameters().get("idContract"));
        	
    	}
    	
    	return success();
    }
    
    //Controlla se è stato definito un contratto
    public Event checkContract (RequestContext context) throws Exception {
		
    	Cause c = (Cause) context.getActiveFlow().getApplicationContext().getBean("cause");
    	
    	if(c.getClaim().getObject1() != null) {
	    	if(c.getClaim().getObject1().equals("contract")) {
	    		return yes();
	    	}
    	
	    	else if(c.getClaim().getObject2() != null) {
	    		if(c.getClaim().getObject2().equals("contract")) {
	    			return yes();
	    		}
	    		else
	    			return no();
	    	}
	    	
	    	else
	    		return no();
    	}
    	else
    		return no();

    	
    }
    
    
 
}