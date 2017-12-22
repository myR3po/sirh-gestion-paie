package dev.paie.service;

import java.util.List;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.web.controller.CotisationRow;

public class BulletinViewModel {

	private BulletinSalaire bulletin;
	private ResultatCalculRemuneration calculRemuneration;
	private List<CotisationRow> cotisationsImposables;
	private List<CotisationRow> cotisationsNonImposables;
	
	public BulletinSalaire getBulletin() {
		return bulletin;
	}
	
	public void setBulletin(BulletinSalaire bulletin) {
		this.bulletin = bulletin;
	}
	
	public ResultatCalculRemuneration getCalculRemuneration() {
		return calculRemuneration;
	}
	
	public void setCalculRemuneration(ResultatCalculRemuneration calculRemuneration) {
		this.calculRemuneration = calculRemuneration;
	}

	public List<CotisationRow> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<CotisationRow> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<CotisationRow> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<CotisationRow> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}
	
	
	
}
