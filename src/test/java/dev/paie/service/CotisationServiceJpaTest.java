package dev.paie.service;

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
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	@Autowired
	private CotisationService cotisationService;

	@Autowired
	private DataSource dataSource;

	@After
	// lancer test si on dans une configuration autre que h2
	public void dropBase() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM cotisation");
		
	}

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder une nouvelle cotisation
		Cotisation cotisation = new Cotisation();
		cotisation.setCode("MO1");
		cotisation.setLibelle("OnNousArnaque");
		cotisation.setTauxPatronal(new BigDecimal("12.02"));
		cotisation.setTauxSalarial(new BigDecimal("2.00"));

		cotisationService.sauvegarder(cotisation);
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la
		// méthode lister

		Cotisation cotisationRecup = cotisationService.lister().stream()
				.filter(c -> c.getCode().equals(cotisation.getCode())).findAny().orElse(null);

		assertThat(cotisationRecup).isNotNull();
		assertThat(cotisationRecup.getLibelle()).isEqualTo(cotisation.getLibelle());
		assertThat(cotisationRecup.getTauxPatronal()).isEqualTo(cotisation.getTauxPatronal());
		// TODO modifier une cotisation
		cotisationRecup.setLibelle("OnNousArnaqueTous");
		cotisationService.mettreAJour(cotisationRecup);
		// TODO vérifier que les modifications sont bien prises en compte via la méthode
		// lister
		Cotisation cotisationRecup2 = cotisationService.lister().stream()
				.filter(c -> c.getCode().equals(cotisation.getCode())).findAny().orElse(null);
		assertThat(cotisationRecup2).isNotNull();
		assertThat(cotisationRecup2.getLibelle()).isEqualTo(cotisationRecup.getLibelle());
		assertThat(cotisationRecup2.getTauxPatronal()).isEqualTo(cotisationRecup.getTauxPatronal());

	}
}
