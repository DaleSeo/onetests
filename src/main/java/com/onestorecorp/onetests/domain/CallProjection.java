package com.onestorecorp.onetests.domain;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inline", types = Call.class)
public interface CallProjection extends DomainProjection {

    Request getRequest();

    Response getResponse();

    Result getResult();

    String getCaseId();

    String getSuiteId();

}