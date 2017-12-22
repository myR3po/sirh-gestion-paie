package dev.paie.service;

import java.time.LocalDate;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	ApplicationContext ctx;
	
	@PersistenceContext
	EntityManager em;
	
	public InitialiserDonneesServiceDev(){
		
	}

	@Override
	@Transactional
	public void initialiser() {
		
		Stream.of(Cotisation.class, ProfilRemuneration.class, Grade.class, Entreprise.class)
				.flatMap(c -> ctx.getBeansOfType(c).values().stream())
				.forEach( b -> {
						em.persist(b);
				});
		
		
		
		for(int month = 1; month <= 12; month++) {
			Periode p = new Periode();
			p.setDateDebut(LocalDate.of(2017, month, 1));
			p.setDateFin(p.getDateDebut().withDayOfMonth(p.getDateDebut().lengthOfMonth()));
			em.persist(p);
		}
		
	}

}
