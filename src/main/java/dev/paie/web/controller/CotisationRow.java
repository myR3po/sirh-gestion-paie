package dev.paie.web.controller;

public class CotisationRow {

	private String rubrique;
	private String tauxSalarial;
	private String montantSalarial;
	private String tauxPatronal ;
	private String cotisationsPatronales;
	
	public String getRubrique() {
		return rubrique;
	}
	
	public void setRubrique(String rubrique) {
		this.rubrique = rubrique;
	}

	public String getTauxSalarial() {
		return tauxSalarial;
	}

	public void setTauxSalarial(String tauxSalarial) {
		this.tauxSalarial = tauxSalarial;
	}

	public String getMontantSalarial() {
		return montantSalarial;
	}

	public void setMontantSalarial(String montantSalarial) {
		this.montantSalarial = montantSalarial;
	}

	public String getTauxPatronal() {
		return tauxPatronal;
	}

	public void setTauxPatronal(String tauxPatronal) {
		this.tauxPatronal = tauxPatronal;
	}

	public String getCotisationsPatronales() {
		return cotisationsPatronales;
	}

	public void setCotisationsPatronales(String cotisationsPatronales) {
		this.cotisationsPatronales = cotisationsPatronales;
	}
	

}
