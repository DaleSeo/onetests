package com.onestorecorp.onetests.domain;

import java.util.Date;

public interface DomainProjection {

	String getId();

	Date getCreatedDate();

	User getCreatedBy();

	Date getLastModifiedDate();

	User getLastModifiedBy();

}
