package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class})
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void dropBase() {
		jdbcTemplate.update("DELETE FROM grade");
	}

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouveau grade
		Grade g = new Grade();
		g.setCode("TEST");
		g.setNbHeuresBase(new BigDecimal("12.02"));
		g.setTauxBase(new BigDecimal("2.00"));
		gradeService.sauvegarder(g);
		
		// TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		Grade gradeRecup = gradeService.lister().stream().distinct().findAny().orElse(null);
		assertThat(gradeRecup.getNbHeuresBase()).isEqualTo(g.getNbHeuresBase());
		assertThat(gradeRecup.getTauxBase()).isEqualTo(g.getTauxBase());
		
		// TODO modifier un grade
		gradeRecup.setCode("MODIFTEST");
		gradeService.mettreAJour(gradeRecup);
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		Grade gradeRecup2 = gradeService.lister().get(0);
		assertThat(gradeRecup2.getCode()).isEqualTo(gradeRecup.getCode());
	}
}
