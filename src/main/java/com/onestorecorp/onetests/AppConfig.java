package com.onestorecorp.onetests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onestorecorp.onetests.domain.*;
import com.onestorecorp.onetests.core.MongoEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableMongoAuditing
public class AppConfig {

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return new RepositoryRestConfigurerAdapter() {

			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.exposeIdsFor(Service.class);
				config.exposeIdsFor(Host.class);
				config.exposeIdsFor(Api.class);
				config.exposeIdsFor(Call.class);
				config.exposeIdsFor(Case.class);
				config.exposeIdsFor(Suite.class);
			}

		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**");
			}
		};
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.simpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.build();
	}

	@Bean
	public MongoEventListener mongoEventListener() {
		return new MongoEventListener();
	}

}