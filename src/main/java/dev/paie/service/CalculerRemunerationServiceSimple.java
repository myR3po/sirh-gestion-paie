package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService
{

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		PaieUtils paieUtils = new PaieUtils();
		ResultatCalculRemuneration rcr = new ResultatCalculRemuneration();
		
		
//		SALAIRE_BASE = GRADE.NB_HEURES_BASE * GRADE.TAUX_BASE
		rcr.setSalaireDeBase(paieUtils.formaterBigDecimal(bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase())));
		BigDecimal salaireBase = new BigDecimal(rcr.getSalaireDeBase());

//		SALAIRE_BRUT = SALAIRE_BASE + PRIME_EXCEPTIONNELLE
		rcr.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBase.add(bulletin.getPrimeExceptionnelle())));
		BigDecimal salaireBrut = new BigDecimal(rcr.getSalaireBrut());

//		TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		
		List<Cotisation> cot = 	bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		
		rcr.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(cot.stream()
				.filter(c -> c.getTauxSalarial() !=null )
				.map(c -> c.getTauxSalarial().multiply(salaireBrut))
				.reduce(BigDecimal::add).get()
		));

//		TOTAL_COTISATIONS_PATRONALES = SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)
		rcr.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(cot.stream()
				.filter(c -> c.getTauxPatronal() !=null)
				.map(c -> c.getTauxPatronal().multiply(salaireBrut))
				.reduce(BigDecimal::add).get()
		));
		
//		NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
		rcr.setNetImposable(paieUtils.formaterBigDecimal(salaireBrut.subtract(new BigDecimal(rcr.getTotalRetenueSalarial()))));
		
//		NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		cot = 	bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables();
		rcr.setNetAPayer(
				paieUtils.formaterBigDecimal(new BigDecimal(rcr.getNetImposable()).subtract(
					cot.stream()
					.filter(c -> c.getTauxSalarial() !=null)
					.map(c -> c.getTauxSalarial().multiply(salaireBrut))
					.reduce(BigDecimal::add).get()			
				)
			));
		
		return rcr;
	}

}
