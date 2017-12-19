package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.sql.DataSource;

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
	private DataSource dataSource;
	
	@Before
	public void dropBase() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM grade");
	}

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouveau grade
		Grade garde = new Grade();
		garde.setCode("TEST");
		garde.setNbHeuresBase(new BigDecimal("12.02"));
		garde.setTauxBase(new BigDecimal("2.00"));
		gradeService.sauvegarder(garde);
		
		// TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		Grade gradeRecup = gradeService.lister().stream().filter( g -> g.getCode().equals(garde.getCode()) ).findAny().orElse(null);
		assertThat(gradeRecup).isNotNull();
		assertThat(gradeRecup.getNbHeuresBase()).isEqualTo(garde.getNbHeuresBase());
		assertThat(gradeRecup.getTauxBase()).isEqualTo(garde.getTauxBase());
		
		// TODO modifier un grade
		gradeRecup.setCode("MODIFTEST");
		gradeService.mettreAJour(gradeRecup);
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		Grade gradeRecup2 = gradeService.lister().get(0);
		assertThat(gradeRecup2.getCode()).isEqualTo(gradeRecup.getCode());
	}
}
