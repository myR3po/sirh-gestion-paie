package dev.paie.service;

import java.util.Map;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;

public interface CalculerRemunerationService {
	
	ResultatCalculRemuneration calculer(BulletinSalaire bulletin);
	
	ResultatCalculRemuneration calculer(Integer bulletinId);
	
	BulletinViewModel calculerForView(Integer bulletinId);
	
	Map<BulletinSalaire, ResultatCalculRemuneration> calculerForAll();

}
