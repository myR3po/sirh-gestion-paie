package dev.paie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import dev.paie.spring.DataSourcesConfig;
import dev.paie.spring.JpaConfig;
import dev.paie.web.controller.CustomAccessDeniedHandler;

@Configuration
@ComponentScan({"dev.paie.service", "dev.paie.util"})
@Import({DataSourcesConfig.class, JpaConfig.class})
@EnableJpaRepositories("dev.paie.repository")
public class ServicesConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
