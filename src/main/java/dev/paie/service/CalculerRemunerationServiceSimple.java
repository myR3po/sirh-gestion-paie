package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
		String zero = "0.00";
		
//		SALAIRE_BASE = GRADE.NB_HEURES_BASE * GRADE.TAUX_BASE
		rcr.setSalaireDeBase(paieUtils.formaterBigDecimal(bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase())));
		BigDecimal salaireBase = new BigDecimal(rcr.getSalaireDeBase());

//		SALAIRE_BRUT = SALAIRE_BASE + PRIME_EXCEPTIONNELLE
		rcr.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBase.add(bulletin.getPrimeExceptionnelle())));
		BigDecimal salaireBrut = new BigDecimal(rcr.getSalaireBrut());

//		TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		
		List<Cotisation> cot = 	bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		
		Optional<BigDecimal> total = cot.stream()
				.filter(c -> c.getTauxSalarial() !=null )
				.map(c -> c.getTauxSalarial().multiply(salaireBrut))
				.reduce(BigDecimal::add);
		
		if(total.isPresent()) {
			rcr.setTotalRetenueSalarial(paieUtils.formaterBigDecimal( total.get()));
		}else {
			rcr.setTotalRetenueSalarial(zero);
		}
		

//		TOTAL_COTISATIONS_PATRONALES = SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)
		total = cot.stream()
				.filter(c -> c.getTauxPatronal() !=null)
				.map(c -> c.getTauxPatronal().multiply(salaireBrut))
				.reduce(BigDecimal::add);
		
		if(total.isPresent()) {
			rcr.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(total.get()));
		}else {
			rcr.setTotalCotisationsPatronales(zero);
		}
		
//		NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
		rcr.setNetImposable(paieUtils.formaterBigDecimal(salaireBrut.subtract(new BigDecimal(rcr.getTotalRetenueSalarial()))));
		
//		NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		cot = 	bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables();
		total = cot.stream()
				.filter(c -> c.getTauxSalarial() !=null)
				.map(c -> c.getTauxSalarial().multiply(salaireBrut))
				.reduce(BigDecimal::add);
		
		if(total.isPresent()) {
			rcr.setNetAPayer(paieUtils.formaterBigDecimal(new BigDecimal(rcr.getNetImposable()).subtract(total.get())));
		}else {
			rcr.setNetAPayer(zero);
		}
		return rcr;
	}

}
