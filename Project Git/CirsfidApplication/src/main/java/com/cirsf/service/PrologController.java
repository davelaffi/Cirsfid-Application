package com.cirsf.service;



import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Util;
import org.jpl7.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.cirsf.model.Cause;
import com.cirsf.model.Claim;
import com.cirsf.model.Contract;
import com.cirsf.model.Person;



public class PrologController extends MultiAction {
	
	@Autowired
	private String prologFiles;
	
	private Cause c;
	
	 public Event evaluateResult(RequestContext context) throws Exception{
		 
	        System.out.println("Eseguo il programma prolog");
	 
	        this.c = (Cause) context.getActiveFlow().getApplicationContext().getBean("cause");
	        
	        c.getResult().setCountry(null);
	        c.getResult().setCourt(null);
	        c.getResult().setErrors(null);
	        
	        System.out.println("Eseguo il programma prolog");
			
	        //Prendo dove sono i file prolog
			System.out.println("" + prologFiles);
			
			//Cerco di capire a quale esempio ci riferiamo
			
			//se ho 1 sola persona e ho ruolo e domicilio
			if(c.getPersons().size() == 1 && c.getPersons().get(0).getDomicile() != null
					&& c.getPersons().get(0).getRole() != null && c.getClaim().getJurisdictionCivil() == null) {
				System.out.println("Ciaossafaf");
				
				try {
					article4();
				}
				catch(Exception e) {
					System.out.println("C'è stata un eccezione nel programma prolog");
					c.getResult().setErrors("Combinazione ancora non aggiunta o errore nella combinazione di campi");
				}
			}
			
			
			else if(c.getClaim().getMatter() != null && c.getClaim().getObject1() != null && c.getPersons().get(0).getRole() != null &&
					c.getClaim().getMatter().equals("civil") && !c.getClaim().getObject1().equals("contract") &&
					c.getClaim().getObject2() != null && c.getPersons().size() == 1 && 
					c.getPersons().get(0).getRole().equals("defendant") && c.getClaim().getSeisedCountry() != null &&
					c.getClaim().getJurisdictionCivil() != null && c.getPersons().get(0).getDomicile() != null) {
				
				try {
					article7_3();
				}
				catch(Exception e) {
					System.out.println("C'è stata un eccezione nel programma prolog");
					c.getResult().setErrors("Combinazione ancora non aggiunta o errore nella combinazione di campi");
				}
			}
			
			else if(c.getClaim().getObject1() != null && c.getClaim().getObject1().equals("contract") &&
					c.getContract() != null && c.getContract().getConsideration() != null &&
					c.getPersons().size() == 2 && 
					c.getPersons().get(0).getNature() != null && c.getPersons().get(1).getNature() != null &&
					c.getPersons().get(0).getRole() != null && c.getPersons().get(1).getRole() != null &&
					c.getContract().getPlaceOfProvision() != null && c.getContract().getPlaceOfProvisionCity() != null) {
				
				try {
					article7_1();
				}
				catch(Exception e) {
					System.out.println("C'è stata un eccezione nel programma prolog");
					c.getResult().setErrors("Combinazione ancora non aggiunta o errore nella combinazione di campi");
				}

			}
			
			else if(c.getClaim().getObject1() != null && c.getClaim().getObject2() != null &&
					c.getClaim().getObject1().equals("contract") && c.getContract().getObligationTarget() != null 
					&& c.getContract().getObligationTargetUse() != null && c.getContract().getObligationTarget().equals("immovableProperty") &&
					c.getContract().getImmovablePropertyCountry() != null && c.getContract().getImmovablePropertyCity() != null &&
					c.getContract().getConsideration() != null &&c.getContract().getType() != null && c.getPersons().size() == 2 && 
					c.getPersons().get(0).getRole() != null && c.getPersons().get(1).getRole() != null &&
					c.getPersons().get(0).getType() != null && c.getPersons().get(1).getType() != null) {
				
				try {
					article24_1();
				}
				catch(Exception e) {
					System.out.println("C'è stata un eccezione nel programma prolog");
					c.getResult().setErrors("Combinazione ancora non aggiunta o errore nella combinazione di campi");
				}
				
			}
			
			else {
				
				c.getResult().setErrors("Combinazione ancora non aggiunta o errore nella combinazione di campi");
				
			}
	        
			//Inizializzo la causa
			this.c.getPersons().clear();
			
	        return success();
	        
	    }
	 
	 
	 //Metodi che mi costruiscono il risultato
	 private void article4() {
		 
		 //carico il DB Prolog
		 	Query temp = new Query("consult('" + prologFiles + "')");
			if(temp.hasSolution()) {
				System.out.println("Database caricato con successo!");
			}
			
			//Creo gli oggetti singoli
			Person p = new Person();
			p.setId(c.getPersons().get(0).getId());
			p.setDomicile(c.getPersons().get(0).getDomicile());
			p.setCityDomicile(c.getPersons().get(0).getCityDomicile());
			
			p.setRole(c.getPersons().get(0).getRole());
			
			Claim claim = new Claim();
			claim.setId(c.getClaim().getId());
			
			//Creo i miei parametri
			Term[] listOfParameters = new Term[] {
					new Compound("personDomicile", new Term[] { new Atom(p.getId()), new Atom(p.getDomicile()), new Atom(p.getCityDomicile())} )
					, new Compound("personRole", new Term[] {new Atom(p.getId()), new Atom(claim.getId()), new Atom(p.getRole())})
			};
			Query q = new Query(
						"asserisciDavide",
						Util.termArrayToList(listOfParameters)	
			);
			System.out.println(q.toString());
			
			if(q.hasSolution()) {
				System.out.println("blabla!");
				
			}
			
			
			
			Query result = new Query("hasJurisdiction", new Term[] {new Variable("Country"),new Variable("Court"), new Atom(claim.getId()), new Atom("brusselsRegulation")});
			
			c.getResult().setCountry(result.oneSolution().get("Country") + "");
			c.getResult().setCourt(result.oneSolution().get("Court") + "");
			c.getResult().setErrors("");
	 }
	 
	 private void article7_3() {
		 
		 	//carico il DB Prolog
		 	Query temp = new Query("consult('" + prologFiles + "')");
			if(temp.hasSolution()) {
				System.out.println("Database caricato con successo!");
			}
			
			//Creo gli oggetti singoli
			Person p = new Person();
			p.setId(c.getPersons().get(0).getId());
			p.setRole(c.getPersons().get(0).getRole());
			p.setDomicile(c.getPersons().get(0).getDomicile());
			p.setCityDomicile(c.getPersons().get(0).getCityDomicile());
			
			Claim claim = new Claim();
			claim.setId(c.getClaim().getId());
			claim.setMatter(c.getClaim().getMatter());
			claim.setObject1(c.getClaim().getObject1());
			if(c.getClaim().getObject2() != null) {
				claim.setObject2(c.getClaim().getObject2());
			}
			claim.setJurisdictionCivil(c.getClaim().getJurisdictionCivil());
			claim.setJurisdictionCivilCity(c.getClaim().getJurisdictionCivilCity());
			claim.setSeisedCountry(c.getClaim().getSeisedCountry());
			claim.setSeisedCity(c.getClaim().getSeisedCity());
			
			System.out.println(p.toString());
			System.out.println(claim.toString());
			
			//Creo i miei parametri
			Term[] listOfParameters = new Term[] {
					new Compound("claimMatter", new Term[] { new Atom(claim.getId()), new Atom(claim.getMatter())} )
					, new Compound("claimObject", new Term[] {new Atom(claim.getId()), new Atom(claim.getObject1())})
					, new Compound("claimObject", new Term[] {new Atom(claim.getId()), new Atom(claim.getObject2())})
					, new Compound("hasJurisdictionOnCivilProceedings", new Term[] {new Atom(claim.getJurisdictionCivil()), new Atom(claim.getJurisdictionCivilCity())})
					, new Compound("personRole", new Term[] {new Atom(p.getId()), new Atom(claim.getId()), new Atom(p.getRole())})
					, new Compound("seised", new Term[] {new Atom(claim.getObject1()), new Atom(claim.getSeisedCountry()), new Atom(claim.getSeisedCity())})
					, new Compound("personDomicile", new Term[] {new Atom(p.getId()), new Atom(p.getDomicile()), new Atom(p.getCityDomicile())})
			};
			Query q = new Query(
						"asserisciDavide",
						Util.termArrayToList(listOfParameters)	
			);
			System.out.println(q.toString());
			
			if(q.hasSolution()) {
				System.out.println("blabla!");
				
			}
			
			Query result = new Query("hasJurisdiction", new Term[] {new Variable("Country"),new Variable("Court"), new Atom(claim.getId()), new Atom("brusselsRegulation")});
			
			if(result.oneSolution().get("Country") != null && result.oneSolution().get("Court") != null) {
				c.getResult().setCountry(result.oneSolution().get("Country") + "");
				c.getResult().setCourt(result.oneSolution().get("Court") + "");
				c.getResult().setErrors("");
			}
			
			else
			{
				System.out.println("Risultato null");
				c.getResult().setErrors("C'e' stato un errore");
			}
	 }
	 
	 private void article7_1() {
		 
		 	//carico il DB Prolog
		 	Query temp = new Query("consult('" + prologFiles + "')");
			if(temp.hasSolution()) {
				System.out.println("Database caricato con successo!");
			}
			
			//Creo gli oggetti singoli
			Person p1 = new Person();
			Person p2 = new Person();
			p1.setId(c.getPersons().get(0).getId());
			p2.setId(c.getPersons().get(1).getId());
			p1.setRole(c.getPersons().get(0).getRole());
			p2.setRole(c.getPersons().get(1).getRole());
			p1.setDomicile(c.getPersons().get(0).getDomicile());
			p2.setDomicile(c.getPersons().get(1).getDomicile());
			p1.setCityDomicile(c.getPersons().get(0).getCityDomicile());
			p2.setCityDomicile(c.getPersons().get(1).getCityDomicile());
			p1.setNature(c.getPersons().get(0).getNature());
			p2.setNature(c.getPersons().get(1).getNature());
			
			if(c.getPersons().get(0).getType() != null)
				p1.setType(c.getPersons().get(0).getType());
			
			if(c.getPersons().get(1).getType() != null)
				p2.setType(c.getPersons().get(1).getType());
			
			Claim claim = new Claim();
			claim.setId(c.getClaim().getId());
			claim.setObject1(c.getClaim().getObject1());
			if(c.getClaim().getObject2() != null) {
				claim.setObject2(c.getClaim().getObject2());
			}
			
			
			Contract contr = new Contract();
			contr.setId(c.getContract().getId());
			contr.setConsideration(c.getContract().getConsideration());
			contr.setType(c.getContract().getType());
			contr.setPlaceOfProvision(c.getContract().getPlaceOfProvision());
			contr.setPlaceOfProvisionCity(c.getContract().getPlaceOfProvisionCity());
			
			Term[] listOfParameters = null;
			
			if(c.getContract().getPlaceOfProvision2() != null && c.getContract().getPlaceOfProvisionCity2() != null) {
				contr.setPlaceOfProvision2(c.getContract().getPlaceOfProvision2());
				contr.setPlaceOfProvisionCity2(c.getContract().getPlaceOfProvisionCity2());

				//Creo i miei parametri
				listOfParameters = new Term[] {
						new Compound("claimObject", new Term[] {new Atom(claim.getId()), new Atom(claim.getObject1()), new Atom(contr.getId())})
						, new Compound("contractTypeConsideration", new Term[] {new Atom(contr.getId()), new Atom(contr.getConsideration())})
						, new Compound("contractType", new Term[] {new Atom(contr.getId()), new Atom(contr.getType())})
						, new Compound("personDomicile", new Term[] {new Atom(p1.getId()), new Atom(p1.getDomicile()), new Atom(p1.getCityDomicile())})
						, new Compound("personDomicile", new Term[] {new Atom(p2.getId()), new Atom(p2.getDomicile()), new Atom(p2.getCityDomicile())})
						, new Compound("personNature", new Term[] {new Atom(p1.getId()), new Atom(p1.getNature())})
						, new Compound("personNature", new Term[] {new Atom(p2.getId()), new Atom(p2.getNature())})
						, new Compound("personRole", new Term[] {new Atom(p1.getId()), new Atom(claim.getId()), new Atom(p1.getRole())})
						, new Compound("personRole", new Term[] {new Atom(p2.getId()), new Atom(claim.getId()), new Atom(p2.getRole())})
						, new Compound("placeOfProvision", new Term[] {new Atom(contr.getPlaceOfProvision()), new Atom(contr.getPlaceOfProvisionCity())})
						, new Compound("placeOfProvision", new Term[] {new Atom(contr.getPlaceOfProvision2()), new Atom(contr.getPlaceOfProvisionCity2())})
				};
				
			}
			
			else {
				
				//Creo i miei parametri
				listOfParameters = new Term[] {
						new Compound("claimObject", new Term[] {new Atom(claim.getId()), new Atom(claim.getObject1()), new Atom(contr.getId())})
						, new Compound("contractTypeConsideration", new Term[] {new Atom(contr.getId()), new Atom(contr.getConsideration())})
						, new Compound("contractType", new Term[] {new Atom(contr.getId()), new Atom(contr.getType())})
						, new Compound("personDomicile", new Term[] {new Atom(p1.getId()), new Atom(p1.getDomicile()), new Atom(p1.getCityDomicile())})
						, new Compound("personDomicile", new Term[] {new Atom(p2.getId()), new Atom(p2.getDomicile()), new Atom(p2.getCityDomicile())})
						, new Compound("personNature", new Term[] {new Atom(p1.getId()), new Atom(p1.getNature())})
						, new Compound("personNature", new Term[] {new Atom(p2.getId()), new Atom(p2.getNature())})
						, new Compound("personRole", new Term[] {new Atom(p1.getId()), new Atom(claim.getId()), new Atom(p1.getRole())})
						, new Compound("personRole", new Term[] {new Atom(p2.getId()), new Atom(claim.getId()), new Atom(p2.getRole())})
						, new Compound("placeOfProvision", new Term[] {new Atom(contr.getPlaceOfProvision()), new Atom(contr.getPlaceOfProvisionCity())})
						
				};
				
			}	
			
			Query q = new Query(
						"asserisciDavide",
						Util.termArrayToList(listOfParameters)	
			);
			System.out.println(q.toString());
			
			if(q.hasSolution())
				System.out.println("Eseguo la Query");
			
			Query result = new Query("hasJurisdiction", new Term[] {new Variable("Country"),new Variable("Court"), new Atom(claim.getId()), new Atom("brusselsRegulation")});
			
			if(result.oneSolution().get("Country") != null && result.oneSolution().get("Court") != null) {
				c.getResult().setCountry(result.oneSolution().get("Country") + "");
				c.getResult().setCourt(result.oneSolution().get("Court") + "");
				c.getResult().setErrors("");
			}
			
			else
			{
				System.out.println("Risultato null");
				c.getResult().setErrors("C'e' stato un errore");
			}
		 
	 }
	 
	 private void article24_1() {
		 
		 	//carico il DB Prolog
		 	Query temp = new Query("consult('" + prologFiles + "')");
			if(temp.hasSolution()) {
				System.out.println("Database caricato con successo!");
			}
			
			//Creo gli oggetti singoli
			Person p1 = new Person();
			Person p2 = new Person();
			p1.setId(c.getPersons().get(0).getId());
			p2.setId(c.getPersons().get(1).getId());
			p1.setRole(c.getPersons().get(0).getRole());
			p2.setRole(c.getPersons().get(1).getRole());
			p1.setDomicile(c.getPersons().get(0).getDomicile());
			p2.setDomicile(c.getPersons().get(1).getDomicile());
			p1.setCityDomicile(c.getPersons().get(0).getCityDomicile());
			p2.setCityDomicile(c.getPersons().get(1).getCityDomicile());
			p1.setType(c.getPersons().get(0).getType());
			p2.setType(c.getPersons().get(1).getType());
			
			
			Claim claim = new Claim();
			claim.setId(c.getClaim().getId());
			claim.setObject1(c.getClaim().getObject1());
			claim.setObject2(c.getClaim().getObject2());
			
			
			Contract contr = new Contract();
			contr.setId(c.getContract().getId());
			contr.setConsideration(c.getContract().getConsideration());
			contr.setObligationTarget(c.getContract().getObligationTarget());
			contr.setObligationTargetUse(c.getContract().getObligationTargetUse());
			contr.setImmovablePropertyCountry(c.getContract().getImmovablePropertyCity());
			contr.setImmovablePropertyCity(c.getContract().getImmovablePropertyCity());
			
			
			//Creo i miei parametri
			Term[] listOfParameters = new Term[] {
					new Compound("claimObject", new Term[] {new Atom(claim.getId()), new Atom(claim.getObject1()), new Atom(contr.getId())})
					, new Compound("claimObject", new Term[] {new Atom(claim.getId()), new Atom(claim.getObject2())})
					, new Compound("contractTypeConsideration", new Term[] {new Atom(contr.getId()), new Atom(contr.getConsideration())})
					, new Compound("personDomicile", new Term[] {new Atom(p1.getId()), new Atom(p1.getDomicile()), new Atom(p1.getCityDomicile())})
					, new Compound("personDomicile", new Term[] {new Atom(p2.getId()), new Atom(p2.getDomicile()), new Atom(p2.getCityDomicile())})
					, new Compound("personRole", new Term[] {new Atom(p1.getId()), new Atom(claim.getId()), new Atom(p1.getRole())})
					, new Compound("personRole", new Term[] {new Atom(p2.getId()), new Atom(claim.getId()), new Atom(p2.getRole())})
					, new Compound("personType", new Term[] {new Atom(p1.getId()), new Atom(p1.getType())})
					, new Compound("personType", new Term[] {new Atom(p2.getId()), new Atom(p2.getType())})
					, new Compound("immovableProperty", new Term[] {new Atom(contr.getImmovablePropertyCountry()), new Atom(contr.getImmovablePropertyCity())})
					, new Compound("contractObligationTarget", new Term[] {new Atom(contr.getId()), new Atom("_"), new Atom(contr.getObligationTarget())})
					, new Compound("contractObligationTargetUse", new Term[] {new Atom(contr.getId()), new Atom("_"), new Atom(contr.getObligationTargetUse())})
					
			};
			Query q = new Query(
						"asserisciDavide",
						Util.termArrayToList(listOfParameters)	
			);
			System.out.println(q.toString());
			
			Query result = new Query("hasJurisdiction", new Term[] {new Variable("Country"),new Variable("Court"), new Atom(claim.getId()), new Atom("brusselsRegulation")});
			
			if(result.oneSolution().get("Country") != null && result.oneSolution().get("Court") != null) {
				c.getResult().setCountry(result.oneSolution().get("Country") + "");
				c.getResult().setCourt(result.oneSolution().get("Court") + "");
				c.getResult().setErrors("");
			}
			
			else
			{
				System.out.println("Risultato null");
				c.getResult().setErrors("C'e' stato un errore");
			}
		 
	 }
	 

}
