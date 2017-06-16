package com.onestorecorp.onetests.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "inline", types = Suite.class)
public interface SuiteProjection extends DomainProjection {

	String getTitle();

	String getDescription();

	String getServiceId();

	Service getService();

	String getHostId();

	List<String> getCaseIds();

	Host getHost();

}
