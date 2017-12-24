package dev.paie.web.form.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class BulletinSalaireForm {

	@NotEmpty(message="Ce champ est requis")
	private String matricule;
	
	@NotNull(message="Ce champ est requis")
	private Integer periode;
	private String primeExceptionnelle;
	
	public String getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}
	
	public void setPrimeExceptionnelle(String primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
	}
	
	public Integer getPeriode() {
		return periode;
	}
	
	public void setPeriode(Integer periode) {
		this.periode = periode;
	}
	
	public String getMatricule() {
		return matricule;
	}
	
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	
}
