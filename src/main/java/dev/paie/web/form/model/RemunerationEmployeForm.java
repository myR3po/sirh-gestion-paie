package dev.paie.web.form.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class RemunerationEmployeForm {
	
	@NotBlank(message="Ce champ est requis")
	private String matricule;
	
	@NotNull(message="Ce champ est requis")
	private Integer entreprise;
	
	@NotNull(message="Ce champ est requis")
	private Integer profilRemuneration;
	
	@NotNull(message="Ce champ est requis")
	private Integer grade;
	
	public String getMatricule() {
		return matricule;
	}
	
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Integer getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Integer entreprise) {
		this.entreprise = entreprise;
	}

	public Integer getProfilRemuneration() {
		return profilRemuneration;
	}

	public void setProfilRemuneration(Integer profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
}
