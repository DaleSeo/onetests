package com.onestorecorp.onetests.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

@Projection(name = "inline", types = Call.class)
public interface CallProjection {

	String getId();

    Date getCreatedDate();

    User getCreatedBy();

    Request getRequest();

    Response getResponse();

    Result getResult();

    String getCaseId();

    String getSuiteId();

}