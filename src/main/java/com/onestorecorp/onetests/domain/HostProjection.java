package com.onestorecorp.onetests.domain;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inline", types = Host.class)
public interface HostProjection extends DomainProjection {

	String getTitle();

	String getDescription();

	String getBaseUrl();

	String getEnvironment();

	String getServiceId();

	Service getService();

}
