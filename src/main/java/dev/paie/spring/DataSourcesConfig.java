package dev.paie.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@PropertySource("classpath:app.properties")
public class DataSourcesConfig {
	
	@Bean
	@Profile(value = { "prod" })
	public DataSource dataSourceProd(@Value("${jdbc.driver}") String driver, @Value("${jdbc.database.url}") String url, @Value("${jdbc.database.user}") String user, @Value("${jdbc.database.password}") String password) {

			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			return dataSource;
	}
	
	@Bean
	@Profile(value = { "dev" })
	public DataSource dataSourceH2() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
		
	}
	
}
