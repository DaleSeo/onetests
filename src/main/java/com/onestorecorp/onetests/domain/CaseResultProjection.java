package com.onestorecorp.onetests.domain;

import org.springframework.data.rest.core.config.Projection;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Projection(name = "inline", types = CaseResult.class)
public interface CaseResultProjection extends DomainProjection {

	boolean isStatusPassed();

	boolean isHeadersPassed();

	boolean isBodyPassed();

	boolean isPassed();

	String getCaseId();

	String getCallId();

	Environment getEnvironment();

	String getError();

	Request getRequest();

	Response getExpected();

	Response getActual();

	long getElapsedTime();

}
