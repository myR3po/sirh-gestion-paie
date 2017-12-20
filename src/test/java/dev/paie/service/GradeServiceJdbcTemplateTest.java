package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	@Autowired
	private GradeService gradeService;

	@Autowired
	private DataSource dataSource;

	@Value("${jdbc.driver}")
	String driver;

	@After
	public void dropBase() {
		if (!"org.h2.Driver".equals(driver)) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update("DELETE FROM grade");
		}
	}

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Grade garde = new Grade();
		garde.setCode("TEST");
		garde.setNbHeuresBase(new BigDecimal("12.02"));
		garde.setTauxBase(new BigDecimal("2.00"));
		gradeService.sauvegarder(garde);

		Grade gradeRecup = gradeService.lister().stream().filter(g -> g.getCode().equals(garde.getCode())).findAny()
				.orElse(null);
		assertThat(gradeRecup).isNotNull();
		assertThat(gradeRecup.getNbHeuresBase()).isEqualTo(garde.getNbHeuresBase());
		assertThat(gradeRecup.getTauxBase()).isEqualTo(garde.getTauxBase());

		gradeRecup.setCode("MODIFTEST");
		gradeService.mettreAJour(gradeRecup);

		Grade gradeRecup2 = gradeService.lister().get(0);
		assertThat(gradeRecup2.getCode()).isEqualTo(gradeRecup.getCode());
	}
}
