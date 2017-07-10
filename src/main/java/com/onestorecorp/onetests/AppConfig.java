package com.onestorecorp.onetests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onestorecorp.onetests.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableCaching
public class AppConfig {

	@Bean
    public WebSecurityConfigurer webSecurityConfigurer() {
	    return new WebSecurityConfigurerAdapter() {

	        @Autowired
            private AuthenticationProvider authProvider;

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                    .csrf().disable()
                    .authorizeRequests()
                        .antMatchers(
                                "/",
                                "/dist/**",
                                "/about",
                                "/signup",
                                "/api/search",
                                "/api/stats",
                                "/api/auth/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
						.and()
					.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/");
            }

			@Autowired
			public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
				auth.authenticationProvider(authProvider)
					.inMemoryAuthentication()
					.withUser("user").password("password").roles("USER");
			}

		};
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

	@Autowired
	private ResponseErrorHandler errorHandler;

	@Bean
	public RestOperations restOperations() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(errorHandler);
		// TODO: can set up better
		return restTemplate;
	}

}