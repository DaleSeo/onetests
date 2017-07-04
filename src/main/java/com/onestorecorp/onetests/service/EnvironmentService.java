package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.component.http.EnvironmentApplier;
import com.onestorecorp.onetests.domain.Environment;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.repository.EnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@org.springframework.stereotype.Service
public class EnvironmentService {

	@Autowired
	private EnvironmentRepository environmentRepo;

	private EnvironmentApplier environmentApplier = new EnvironmentApplier();

	public void applyEnvironmentToRequest(Request req, String environmentId) {
		if (environmentId == null) {
			return;
		}
		Environment environment = environmentRepo.findOne(environmentId);
		environmentApplier.apply(req, environment);
	}

	public void applyEnvironmentToRequest(Request req, Environment environment, Service service) {
		if (environment == null) {
			environment = service.getEnvironment();
		}
		environmentApplier.apply(req, environment);
	}

}
