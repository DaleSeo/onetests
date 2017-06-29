package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Environment;
import com.onestorecorp.onetests.domain.Request;

import java.util.Map;

public class EnvironmentApplier {

	private static final String HOST_HOLDER = "[[HOST]]";

	public Request apply(Request req, Environment environment) {
		if (environment == null) return req;

		if (HOST_HOLDER.equals(req.getHost())) {
			req.setHost(environment.getHost());
		}

		Map<String, String> variables = environment.getVariables();

		req.getHeaders().entrySet()
				.forEach(entry -> {
					if (!(entry.getValue().startsWith("[[") && entry.getValue().endsWith("]]"))) return;
					String value = entry.getValue().substring(2, entry.getValue().length() - 2);
					if (variables.keySet().contains(value)) {
						entry.setValue(variables.get(value));
					}
				});

		return req;
	}


}
