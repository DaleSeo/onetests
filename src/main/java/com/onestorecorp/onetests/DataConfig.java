package com.onestorecorp.onetests;

import com.onestorecorp.onetests.core.AuditorAwareImpl;
import com.onestorecorp.onetests.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
@EnableMongoAuditing
public class DataConfig {

	@Bean
	public AuditorAware<User> auditorAware() {
		return new AuditorAwareImpl();
	}

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return new RepositoryRestConfigurerAdapter() {

			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.exposeIdsFor(User.class);
				config.exposeIdsFor(Service.class);
				config.exposeIdsFor(Host.class);
				config.exposeIdsFor(Api.class);
				config.exposeIdsFor(Call.class);
				config.exposeIdsFor(Case.class);
				config.exposeIdsFor(CaseResult.class);
				config.exposeIdsFor(Suite.class);
				config.exposeIdsFor(SuiteResult.class);
				config.exposeIdsFor(Setting.class);
				config.exposeIdsFor(Environment.class);
				config.exposeIdsFor(Comment.class);
			}

		};
	}

}
