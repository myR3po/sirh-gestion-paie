package dev.paie.web.view;

import java.util.List;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.web.view.CotisationRowModel;

public class BulletinViewModel {

	private BulletinSalaire bulletin;
	private ResultatCalculRemuneration calculRemuneration;
	private List<CotisationRowModel> cotisationsImposables;
	private List<CotisationRowModel> cotisationsNonImposables;
	
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

	public List<CotisationRowModel> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<CotisationRowModel> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<CotisationRowModel> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<CotisationRowModel> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}
	
	
	
}
