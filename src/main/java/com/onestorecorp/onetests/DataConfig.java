package com.onestorecorp.onetests;

import com.onestorecorp.onetests.core.AuditorAwareImpl;
import com.onestorecorp.onetests.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class DataConfig {

	@Bean
	public AuditorAware<User> auditorAware() {
		return new AuditorAwareImpl();
	}

}
