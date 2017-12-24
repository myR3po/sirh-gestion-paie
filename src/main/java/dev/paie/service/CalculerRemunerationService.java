package dev.paie.service;

import java.util.Map;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.web.view.BulletinViewModel;

public interface CalculerRemunerationService {
	
	ResultatCalculRemuneration calculer(BulletinSalaire bulletin);
	
	ResultatCalculRemuneration calculer(Integer bulletinId);
	
	BulletinViewModel calculerForView(Integer bulletinId);
	
	Map<BulletinSalaire, ResultatCalculRemuneration> calculerForAll();

}
