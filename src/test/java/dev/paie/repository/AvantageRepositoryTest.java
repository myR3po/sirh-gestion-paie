package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {
	@Autowired
	private AvantageRepository avantageRepository;

	@Autowired
	private DataSource dataSource;

	@After
	public void dropBase() {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update("DELETE FROM avantage");
	}

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		Avantage avantage = new Avantage();
		avantage.setCode("AV_TEST");
		avantage.setNom("impot");
		avantage.setMontant(new BigDecimal("12.30"));

		assertThat(avantage.getId()).isNull();

		// TODO sauvegarder un nouvel avantage
		avantage = avantageRepository.save(avantage);
		assertThat(avantage.getId()).isNotNull();

		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la
		// méthode findOne
		Avantage recup = avantageRepository.findOne(avantage.getId());
		assertThat(recup.getCode()).isEqualTo(avantage.getCode());

		// TODO modifier un avantage
		recup.setMontant(new BigDecimal("1200.00"));
		avantageRepository.save(recup);

		// TODO vérifier que les modifications sont bien prises en compte via la méthode
		// findOne
		Avantage recup2 = avantageRepository.findByCode(recup.getCode());
		assertThat(recup2.getCode()).isEqualTo(avantage.getCode());
		assertThat(recup2.getMontant()).isEqualTo(recup.getMontant());
	}
}
