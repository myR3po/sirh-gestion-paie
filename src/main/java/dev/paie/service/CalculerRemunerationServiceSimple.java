package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.util.PaieUtils;
import dev.paie.web.view.BulletinViewModel;
import dev.paie.web.view.CotisationRowModel;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService
{

	@Autowired
	BulletinSalaireRepository bulletinSalaireRepository;
	
	@Autowired
	PaieUtils paieUtils;
	
	private Function<Cotisation, CotisationRowModel> cotisationToCotisationRow(BigDecimal salaireBrut){
		
		return c -> {
			CotisationRowModel cr = new CotisationRowModel();
			
			cr.setRubrique(c.getLibelle());
			
			if(c.getTauxSalarial() != null) {
				cr.setMontantSalarial( paieUtils.formaterBigDecimal(c.getTauxSalarial().multiply(salaireBrut )));
				cr.setTauxSalarial(paieUtils.formaterBigDecimal(c.getTauxSalarial()));
			}
			
			if(c.getTauxPatronal() != null) {
				cr.setCotisationsPatronales(paieUtils.formaterBigDecimal(c.getTauxPatronal().multiply(salaireBrut)));
				cr.setTauxPatronal(paieUtils.formaterBigDecimal(c.getTauxPatronal()));
			}
			
			
			return cr;
			};
	}
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
//		PaieUtils paieUtils = new PaieUtils();
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
	
	@Transactional
	public ResultatCalculRemuneration calculer(Integer bulletinId) {
		BulletinSalaire bulletin =  bulletinSalaireRepository.findOne(bulletinId);
		return calculer(bulletin);
	}
	
	@Transactional
	public BulletinViewModel calculerForView(Integer bulletinId) {
				
		BulletinViewModel bulletinView = new BulletinViewModel();
		
		BulletinSalaire bulletin =  bulletinSalaireRepository.findOne(bulletinId);
		bulletinView.setBulletin(bulletin);
		
		ResultatCalculRemuneration rcr = calculer(bulletin);
		BigDecimal salaireBrut = new BigDecimal(rcr.getSalaireBrut());
		bulletinView.setCalculRemuneration(calculer(bulletin));
		
		List<CotisationRowModel> cotisation =  bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables().stream().map(cotisationToCotisationRow(salaireBrut) ).collect(Collectors.toList());
		bulletinView.setCotisationsImposables(cotisation);
		
		cotisation =  bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().stream().map(cotisationToCotisationRow(salaireBrut) ).collect(Collectors.toList());
		bulletinView.setCotisationsNonImposables(cotisation);
		
		return bulletinView;
	}
	
	@Override
	@Transactional
	public Map<BulletinSalaire, ResultatCalculRemuneration> calculerForAll() {
		List<BulletinSalaire> bulletins = bulletinSalaireRepository.findAll();
		return bulletins.stream().collect(Collectors.toMap(bul -> bul, bul -> calculer(bul)));
	}
	
}
