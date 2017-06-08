package com.onestorecorp.onetests.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "inline", types = Case.class)
public interface CaseProjection extends DomainProjection {

	String getTitle();

	String getDescription();

	Request getRequest();

	Response getResponse();

	List<String> getExclusions();

	String getServiceId();

	Service getService();

	String getHostId();

	Host getHost();

}
